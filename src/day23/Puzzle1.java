package day23;

/**
 * Crab Cups part 1. Uses a singly-linked list stored in an array to represent
 * the order of cups.
 */
public class Puzzle1 {

    /**
     * Total number of cups.
     */
    static int total;

    /**
     * Number of current cup.
     */
    static int current;
    
    /**
     * Neighbors of each cup. Zero'th element stays unused.
     */
    static int[] next;
    
    /**
     * Sets neighbor of cup i to j.
     */
    static void setNext(int i, int j) {
        next[i] = j;
    }

    /**
     * Gets neighbor of cup i.
     */
    static int getNext(int i) {
        return next[i];
    }

    /**
     * Starts a new game with the given start configuration and a given total
     * number of cups.
     */
    static void init(String start, int cups) {
        total = cups;
        
        next = new int[total + 1];

        current = start.charAt(0) - '0';
        
        // Link start cups with each other.
        int j = current;
        for (int i = 1; i < start.length(); i++) {
            int k = start.charAt(i) - '0';
            
            System.out.println("" + j + " -> " + k);
            
            setNext(j, k);
            j = k;
        }

        // Link remaining cups (if any).
        System.out.println("[....]");
        for (int i = start.length() + 1; i <= cups; i++) {
            // System.out.println("" + j+ " -> " + i);
            setNext(j, i);
            j = i;
        }

        // Link last cup with first in start configuration.
        System.out.println("" + cups + " -> " + current);
        setNext(j, current);
        
        System.out.println();
    }
    
    /**
     * Plays the given number of rounds, wildly shuffling and swapping our cups.
     */
    static void play(int rounds) {
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
                System.out.print('#');
            }
        }
        
        System.out.println();
        System.out.println();
    }

    /**
     * Prints the solution (neighbors of cup 1, product of first and second).
     */
    static void done() {
        int cup1 = getNext(1);
        int cup2 = getNext(cup1);
        
        long product = (long)cup1 * (long)cup2; // Caution: Large value!
        
        System.out.print("Neighbors of 1 = ");
        for (int i = 1; i < 9; i++) {
            System.out.print(cup1 + " ");
            cup1 = getNext(cup1);
        }        
        System.out.println();
        
        System.out.print("First * second = " + product);
        System.out.println();
    }   
    
    public static void main(String[] args) {
        init("398254716", 9);
        play(100);
        done();
    }
}
