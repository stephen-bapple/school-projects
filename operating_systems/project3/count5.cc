/*
 * Simple program that counts to 5.
 * Intended to be used with CPU.cc as a process to manage.
 * Author: Stephen Bapple
 */
#include<unistd.h>

int main()
{
   // Count to 5.
   for (int i = 0; i < 5; i++)
   {
      sleep(1);
   }
}

