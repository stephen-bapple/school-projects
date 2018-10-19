/*
 * Program that counts to a number passed in as an argument and returns
 * the highest number it was able to count to.
 * Author: Stephen Bapple
 */
#include<stdlib.h>
#include<stdio.h>
#include<unistd.h>

int main(int argc, char** argv) {
  pid_t pid = getpid();
  int n = 0;

  if (argc < 2) {
     printf("Process %i: Usage: <program name> <number to count to>\n", pid);
     return n;
  }
 
  char* temp;
  int maxCount = strtol(argv[1], &temp, 10);

  if (*temp != '\0') {
    printf("Process %i: Invalid counter passed, '%s' should be an integer.\n",\
           pid, argv[1]);
    return n;
  }

  while(n < maxCount) {
    printf("Process: %i %i\n", pid, ++n);
  }

  return n; 
}

