/**
 * Crab Cups parts 1 and 2 in C (derived from Java solution).
 */
#import <string.h>
#import <stdio.h>

/**
 * Total number of cups.
 */
int total;

/**
 * Number of current cup.
 */
int current;

/**
 * Neighbors of each cup. Zero'th element stays unused.
 */
int next[10000001];
    
/**
 * Sets neighbor of cup i to j.
 */
void setNext(int i, int j) {
    next[i] = j;
}

/**
 * Gets neighbor of cup i.
 */
int getNext(int i) {
    return next[i];
}

/**
 * Starts a new game with the given start configuration and a given total
 * number of cups.
 */
void init(char* start, int cups) {
    total = cups;

    current = start[0] - '0';

    // Link start cups with each other.
    int j = current;
    for (int i = 1; i < strlen(start); i++) {
        int k = start[i] - '0';

        printf("%d -> %d\n", j, k);

        setNext(j, k);
        j = k;
    }

    // Link remaining cups (if any).
    puts("[....]");
    for (int i = strlen(start) + 1; i <= cups; i++) {
        // System.out.println("" + j+ " -> " + i);
        setNext(j, i);
        j = i;
    }

    // Link last cup with first in start configuration.
    printf("%d -> %d\n", cups, current);
    setNext(j, current);

    puts("");
}
    
/**
 * Plays the given number of rounds, wildly shuffling and swapping our cups.
 */
void play(int rounds) {
    while (rounds-- > 0) {
        // Pick
        int hand = getNext(current);
        setNext(current, getNext(getNext(getNext(hand))));

        // Place
        int dest = current;
        do {
            dest--;
            if (dest == 0) {
                dest = total;
            }
        } while (dest == hand || dest == getNext(hand) || dest == getNext(getNext(hand)));

        setNext(getNext(getNext(hand)), getNext(dest));
        setNext(dest, hand);

        // Rotate
        current = getNext(current);

        if (rounds % 10000 == 0) {
             putchar('#');
        }
    }

    puts("");
    puts("");
}

/**
 * Prints the solution (neighbors of cup 1, product of first and second).
 */
void done() {
    int cup1 = getNext(1);
    int cup2 = getNext(cup1);

    long product = (long)cup1 * (long)cup2; // Caution: Large value!

    printf("Neighbors of 1 = ");
    for (int i = 1; i < 9; i++) {
        printf("%d ", cup1);
        cup1 = getNext(cup1);
    }        
    puts("");

    printf("First * second = %ld\n\n", product);
}   
    
int main() {
    puts("*** AoC 2020.23 Crab Cups ***");
    puts("");

    puts("--- Part 1 ---");
    puts("");

    init("398254716", 9);
    play(100);
    done();

    puts("--- Part 2 ---");
    puts("");

    init("398254716", 1000000);
    play(10000000);
    done();

    return 0;
}
