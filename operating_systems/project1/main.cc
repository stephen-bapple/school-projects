/*
 * Program that forks and executes a process.
 * Author: Stephen Bapple.
 */
#include<stdio.h>
#include<unistd.h>
#include<sys/wait.h>

int main(int argc, char** argv) {
  pid_t pid = getpid();
  pid_t cpid = fork();
  
  if (cpid == -1) { //error
    perror("fork"); 
    return -1;

  } else if (cpid == 0) { // in child process
    int error = execl("./counter", "counter", "5", (char*)NULL);
    
    if (error) {
      perror("execl");
      return -1;
    }

  } else { // in parent process
    printf("Child PID: %i\n", cpid);
    printf("Parent PID: %i\n", pid);

    int status;
    wait(&status);
    
    if (WIFEXITED(status)) {
      printf("Process %i exited with status %i\n", cpid, WEXITSTATUS(status));
      
      if (WEXITSTATUS(status) == 5) {
        return 0; // Successful execution.
      } else {
        return -1; // Something went wrong, as desribed above.
      }

    } else if (WIFSIGNALED(status)) {
      printf("Process %i was terminated by signal: %i\n", cpid,\
             WTERMSIG(status));

    } else {
      printf("Unidentifiable error. This should never happen.");
    }

    return -1; // Failure; execution successful only if WIFEXITED is true.
  }
}
