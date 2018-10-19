#include <iostream>
#include <list>
#include <iterator>
#include <unistd.h>
#include <signal.h>
#include <errno.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <string.h>
/*
** CPU simulation program version 2.
**
** This program does the following.
** 1) Create handlers for two signals.
** 2) Create an idle process which will be executed when there is nothing
**    else to do.
** 3) Create a send_signals process that sends a SIGALRM every so often
**
** Authors Dr. Steve Beaty (professor), Stephen Bapple (student).
**
*/

#define NUM_SECONDS 20

#define K2P 0
#define P2K 1

#define READ_END 0
#define WRITE_END 1

// make sure the asserts work
#undef NDEBUG
#include <assert.h>

#define EBUG
#ifdef EBUG
#   define dmess(a) cout << "in " << __FILE__ << \
    " at " << __LINE__ << " " << a << endl;

#   define dprint(a) cout << "in " << __FILE__ << \
    " at " << __LINE__ << " " << (#a) << " = " << a << endl;

#   define dprintt(a,b) cout << "in " << __FILE__ << \
    " at " << __LINE__ << " " << a << " " << (#b) << " = " \
    << b << endl
#else
#   define dprint(a)
#endif /* EBUG */

using namespace std;

enum STATE { NEW, RUNNING, WAITING, READY, TERMINATED };

int eye2eh (int i, char *buf, int bufsize, int base)
{
    if (bufsize < 1) return (-1);
    buf[bufsize-1] = '\0';
    if (bufsize == 1) return (0);
    if (base < 2 || base > 16)
    {
        for (int j = bufsize-2; j >= 0; j--)
        {
            buf[j] = ' ';
        }
        return (-1);
    }

    int count = 0;
    const char *digits = "0123456789ABCDEF";
    for (int j = bufsize-2; j >= 0; j--)
    {
        if (i == 0)
        {
            buf[j] = ' ';
        }
        else
        {
            buf[j] = digits[i%base];
            i = i/base;
            count++;
        }
    }
    if (i != 0) return (-1);
    return (count);
}

/*
** a signal handler for those signals delivered to this process, but
** not already handled.
*/
void grab (int signum) { dprint (signum); }

// c++decl> declare ISV as array 32 of pointer to function (int) returning
// void
void (*ISV[32])(int) = {
/*        00    01    02    03    04    05    06    07    08    09 */
/*  0 */ grab, grab, grab, grab, grab, grab, grab, grab, grab, grab,
/* 10 */ grab, grab, grab, grab, grab, grab, grab, grab, grab, grab,
/* 20 */ grab, grab, grab, grab, grab, grab, grab, grab, grab, grab,
/* 30 */ grab, grab
};

struct PCB
{
    STATE state;
    const char *name;   // name of the executable
    int pid;            // process id from fork();
    int ppid;           // parent process id
    int interrupts;     // number of times interrupted
    int switches;       // may be < interrupts
    int started;        // the time this process started
    int pipes[2][2];    // pipes: process to kernel and kernel to process.
};

/*
** an overloaded output operator that prints a PCB
*/
ostream& operator << (ostream &os, struct PCB *pcb)
{
    os << "state:        " << pcb->state << endl;
    os << "name:         " << pcb->name << endl;
    os << "pid:          " << pcb->pid << endl;
    os << "ppid:         " << pcb->ppid << endl;
    os << "interrupts:   " << pcb->interrupts << endl;
    os << "switches:     " << pcb->switches << endl;
    os << "started:      " << pcb->started << endl;
    return (os);
}

/*
** an overloaded output operator that prints a list of PCBs
*/
ostream& operator << (ostream &os, list<PCB *> which)
{
    list<PCB *>::iterator PCB_iter;
    for (PCB_iter = which.begin(); PCB_iter != which.end(); PCB_iter++)
    {
        os << (*PCB_iter);
    }
    return (os);
}

PCB *running;
PCB *idle;

// http://www.cplusplus.com/reference/list/list/
list<PCB *> new_list;
list<PCB *> processes;

int sys_time;

/*
**  send signal to process pid every interval for number of times.
*/
void send_signals (int signal, int pid, int interval, int number)
{
    dprintt ("at beginning of send_signals", getpid ());

    for (int i = 1; i <= number; i++)
    {
        sleep (interval);

        dprintt ("sending", signal);
        dprintt ("to", pid);

        if (kill (pid, signal) == -1)
        {
            perror ("kill");
            return;
        }
    }
    dmess ("at end of send_signals");
}

struct sigaction *create_handler (int signum, void (*handler)(int))
{
    struct sigaction *action = new (struct sigaction);

    action->sa_handler = handler;
/*
**  SA_NOCLDSTOP
**  If  signum  is  SIGCHLD, do not receive notification when
**  child processes stop (i.e., when child processes  receive
**  one of SIGSTOP, SIGTSTP, SIGTTIN or SIGTTOU).
*/
    if (signum == SIGCHLD)
    {
        action->sa_flags = SA_NOCLDSTOP;
    }
    else
    {
        action->sa_flags = 0;
    }

    sigemptyset (&(action->sa_mask));

    assert (sigaction (signum, action, NULL) == 0);
    return (action);
}

PCB* choose_process ()
{
    // Interrrupt running process. switches is not updated here because
    // the current process might be the only ready one.
    running->state = READY;
    running->interrupts++;

    // If the new list is empty, round robin among the ready processes.
    // Otherwise, start the first process on the new list.
    if (new_list.empty())
    {
        list<PCB *>::iterator iter;

        // Search for a ready process.
        for (iter = processes.begin(); iter != processes.end(); iter++) 
        {
            PCB *current = *iter;

            // Found a ready process.
            if (current->state == READY)
            {
                // Only update switches if CPU actually switches processes.
                if (running != current)
                {
                    running->switches++;
                    running = current;
                } 

                // Move the selected process to the back of the queue.
                processes.remove(running);
                processes.push_back(running);

                running->state = RUNNING;
                return running;
            }
        }

        // If there are no ready processes, return idle.
        running->switches++; // Context switch performed.
        idle->state = RUNNING;
        return idle;
    }
    else
    {
        // Get the first process.
        PCB *new_process = new_list.front();
        new_list.pop_front();
        processes.push_back(new_process);

        // Update context switches and change to the new process.
        running->switches++;
        running = new_process;
        running->state = RUNNING;
        running->started = sys_time;

        //pipes
        assert(pipe(running->pipes[P2K]) == 0);
        assert(pipe(running->pipes[K2P]) == 0);

        // Make the read end of the kernel pipe non-blocking.
        assert(fcntl(running->pipes[P2K][READ_END], F_SETFL,
               fcntl(running->pipes[P2K][READ_END], F_GETFL)
               | O_NONBLOCK) == 0);



        int cpid;
        assert((cpid = fork()) != -1);

        if (cpid == 0) // In child process.
        {
            // Close unused ends.
            close (running->pipes[P2K][READ_END]);
            close (running->pipes[K2P][WRITE_END]);

            // Assign fildes 3 nd 4 to the pipe ends in the child.
            dup2 (running->pipes[P2K][WRITE_END], 3);
            dup2 (running->pipes[K2P][READ_END], 4);
            
            assert(execl(running->name, running->name, (char*)NULL) != -1);
        }
        else           // In parent process.
        {
            running->pid = cpid;
        }

        return running;
    }
}

void scheduler (int signum)
{
    assert (signum == SIGALRM);
    sys_time++;

    PCB* tocont = choose_process();

    dprintt ("continuing", tocont->pid);
    if (kill (tocont->pid, SIGCONT) == -1)
    {
        perror ("kill");
        return;
    }
}

void process_done (int signum)
{
    assert (signum == SIGCHLD);

    int status, cpid;

    cpid = waitpid (-1, &status, WNOHANG);

    dprintt ("in process_done", cpid);

    if  (cpid == -1)
    {
        perror ("waitpid");
    }
    else if (cpid == 0)
    {
        if (errno == EINTR) { return; }
        perror ("no children");
    }
    else
    {
        dprint (WEXITSTATUS (status));

        // Change running state to terminated so the right state will print.
        running->state = TERMINATED;

        // Print information about the terminated process.
        cout << "Terminated process: " << endl << running << "elapsed time: "\
            << (sys_time - running->started) << endl;

        // Continue the idle process.
        running = idle;
        running->state = RUNNING;
        dprintt ("restarting idle", idle->pid);
        assert (kill (running->pid, SIGCONT) != -1);
    }
}

const char* handle_message(char* buffer)
{
    if (strcmp(buffer, "TIME") == 0)
    {
        dprintt("received kernel request for time from", running->pid);
        char *time = new char[3];
        
        assert(eye2eh(sys_time, time, 3, 10) != -1);
        
        string time_message = "System time is: ";
        string time_num(time, 3);
	time_message = time_message + time_num;

        const char *ret = time_message.c_str();
        return(ret);
    }
    else if (strcmp(buffer, "PS") == 0) 
    {
        dprintt("received kernel request for process list from", running->pid);

        string process_strings = "Current processes:\n";

        list<PCB *>::iterator iter;
        for (iter = processes.begin(); iter != processes.end(); iter++) 
        {
            PCB *current = *iter;
            process_strings = process_strings + "Process name: "\
                                              + current->name + "\n";
        }

        const char *ret = process_strings.c_str();
        return(ret);
    }

    return("Invalid request to kernel.");
}

void process_trap (int signum)
{
    dprintt("in process_trap", running->pid);
    assert (signum == SIGTRAP);

    running->state = WAITING; 

    char buf[1024];
    int num_read = read (running->pipes[P2K][READ_END], buf, 1023);

    if (num_read > 0) 
    {
       buf[num_read] = '\0';

       const char *message = handle_message(buf);     
       write(running->pipes[K2P][WRITE_END], message, strlen(message));
    }
    
    running->state = RUNNING; 
}

/*
** stop the running process and index into the ISV to call the ISR
*/
void ISR (int signum)
{
    if (kill (running->pid, SIGSTOP) == -1)
    {
        perror ("kill");
        return;
    }
    dprintt ("stopped", running->pid);

    ISV[signum](signum);
}

/*
** set up the "hardware"
*/
void boot (int pid)
{
    ISV[SIGALRM] = scheduler;       create_handler (SIGALRM, ISR);
    ISV[SIGCHLD] = process_done;    create_handler (SIGCHLD, ISR);
    ISV[SIGTRAP] = process_trap;    create_handler (SIGTRAP, ISR);

    // start up clock interrupt
    int ret;
    if ((ret = fork ()) == 0)
    {
        // signal this process once a second for three times
        send_signals (SIGALRM, pid, 1, NUM_SECONDS);

        // once that's done, really kill everything...
        kill (0, SIGTERM);
    }

    if (ret < 0)
    {
        perror ("fork");
    }
}

void create_idle ()
{
    int idlepid;

    if ((idlepid = fork ()) == 0)
    {
        dprintt ("idle", getpid ());

        // the pause might be interrupted, so we need to
        // repeat it forever.
        for (;;)
        {
            dmess ("going to sleep");
            pause ();
            if (errno == EINTR)
            {
                dmess ("waking up");
                continue;
            }
            perror ("pause");
        }
    }
    idle = new (PCB);
    idle->state = RUNNING;
    idle->name = "IDLE";
    idle->pid = idlepid;
    idle->ppid = 0;
    idle->interrupts = 0;
    idle->switches = 0;
    idle->started = sys_time;
}

int main (int argc, char **argv)
{
    int pid = getpid();
    dprintt ("main", pid);

    sys_time = 0;

    boot (pid);


    // create a process to soak up cycles
    create_idle ();
    running = idle;

    cout << running;

    // Add all the arguments to the new list.
    int i;
    for (i = 1; i < argc; i++) {
        PCB *new_process; 
        new_process = new (PCB);
        new_process->state = NEW;
        new_process->name = argv[i];
        new_process->ppid = pid;
        new_process->interrupts = 0;
        new_process->switches = 0;

        new_list.push_back(new_process);
    }

    // we keep this process around so that the children don't die and
    // to keep the IRQs in place.
    for (;;)
    {
        pause();
        if (errno == EINTR) { continue; }
        perror ("pause");
    }
}
