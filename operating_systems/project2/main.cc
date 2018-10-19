/*
 * Signal handling program.
 * Author: Stephen Bapple
 */
#include<stdio.h>
#include<unistd.h>
#include<assert.h>
#include<signal.h>
#include<sys/wait.h>
#include<errno.h>
#include<string.h>

void handler(int num) {
  const char *message;

  if (num == SIGALRM) {
    message = "Handled alarm signal.\n";
  } else if (num == SIGUSR1) {
    message = "Handled user-defined signal #1\n";
  } else if (num == SIGUSR2) {
    message = "Handled user-defined signal #2\n";
  } else {
    message = "Error. Tried to handle unsupported signal.\n";
  }

  assert(write(1, message, strlen(message)) != -1);

}

struct sigaction* setup_sigaction(int signum) {
  struct sigaction *action = new (struct sigaction);
  action->sa_handler = handler;
  action->sa_flags = SA_RESTART;
  sigemptyset(&(action-> sa_mask));
  assert(sigaction(signum, action, NULL) == 0);

  return action; // So we have a reference to delete later.
}

int main() {  
  int success_code = -1;

  // Set up the signal handlers.
  struct sigaction *act1 = setup_sigaction(SIGALRM);
  struct sigaction *act2 = setup_sigaction(SIGUSR1);
  struct sigaction *act3 = setup_sigaction(SIGUSR2);

  pid_t pid = getpid();
  pid_t cpid = fork();

  if (cpid == -1) { //error
    perror("fork"); 
    return -1;

  } else if (cpid == 0) { // in child process
    // Hardcode the signals to send.
    kill(pid, SIGALRM);
    kill(pid, SIGUSR1);
    kill(pid, SIGUSR2);
    kill(pid, SIGUSR2);
    kill(pid, SIGUSR2);
    
    success_code = 0; // Everything went OK.

  } else { // in parent process 
    int keep_waiting = 1;

    while (keep_waiting) {
      int status;
      int retpid = waitpid(cpid, &status, 0);
        
      if (retpid == -1) {
        if(errno == EINTR) {
          // Try again, skip the rest of the while loop.
          printf("Had to restart waitpid\n");
          continue;
        } else {
          perror("waitpid");
          break; // Cannot recover, get out of while loop.
        }
      }
      assert(retpid == cpid);

      if (WIFEXITED(status)) {
        keep_waiting = 0;
        success_code = 0;
      } else if (WIFSIGNALED(status)) {
        printf("Child process was terminated by a signal.\n\n");
        keep_waiting = 0;
      } else {
        printf("Unidentifed error: this should never happen.\n\n");
        keep_waiting = 0;
      }
    }
  }

  // Clean up structs for both parent and child.
  delete (act1);
  delete (act2);
  delete (act3); 

  return success_code;
}
