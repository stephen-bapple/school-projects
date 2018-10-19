#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

/*
** Compile and run this program, and make sure you get the 'aargh' error
** message. Fix it using a pthread mutex. The one command-line argument is
** the number of times to loop. Here are some suggested initial values, but
** you might have to tune them to your machine.
** Debian 8: 100000000
** Gouda: 10000000
** OS X: 100000
** You will need to compile your program with a "-lpthread" option.
**
** Authors: Dr. Steve Beaty (professor), Stephen Bapple (student).
** Operating Systems Summer 2017
*/

#define NUM_THREADS 2

int i;
pthread_mutex_t loop_mutex;

void *foo (void *bar)
{
    pthread_t *me = new pthread_t (pthread_self());
    printf("in a foo thread, ID %ld\n", *me);

    pthread_mutex_lock(&loop_mutex);
    for (i = 0; i < *((int *) bar); i++)
    {
        int tmp = i;

        if (tmp != i)
        {
            printf ("aargh: %d != %d\n", tmp, i);
        }
    }
    pthread_mutex_unlock(&loop_mutex);

    pthread_exit (me);
}

int main(int argc, char **argv)
{
    int iterations = strtol(argv[1], NULL, 10);
    pthread_t threads[NUM_THREADS];

    for (int i = 0; i < NUM_THREADS; i++)
    {
        if (pthread_create(&threads[i], NULL, foo, (void *) &iterations))
        {
            perror ("pthread_create");
            return (1);
        }
    }

    for (int i = 0; i < NUM_THREADS; i++)
    {
        void *status;
        if (pthread_join (threads[i], &status))
        {
            perror ("pthread_join");
            return (1);
        }
        printf("joined a foo thread, number %ld\n", *((pthread_t *) status));
    }

    return (0);
}
