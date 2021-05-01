package day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Shuttle Search part 2.
 */
public class Puzzle2 {
    
    /**
     * Finds the earliest point in time equal to or following 'start' where
     * buses 'bus1' and 'bus2' meet with time difference of 'offset'. We use
     * an iterative approach that increments our point of time by the cycle
     * of bus 1 (which is good because 'bus1' grows pretty large over time).
     */
    long match(long start, long bus1, long bus2, long offset) {
        long value = start;
        while (true) {
            if ((value + offset) % bus2 == 0) {
                System.out.println("Bus " + bus1 + " (offset " + start + ") and bus " + bus2 + " (offset " + offset + ") meet at " + value);
                return value;
            }
            value = value + bus1;
        }
    }
    
    void process(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            reader.readLine(); // Ignore arrival for part 1.
            
            String[] s = reader.readLine().split(",");
            
            // Algorithm idea: Find the earliest point in time where two buses
            // meet (with the given offset), then merge these buses into a
            // "virtual bus" the cycle time of which is the product of the
            // individual cycles and the offset is the point in time. Try to
            // merge the next bus from that point forward (cannot be earlier).
            
            long cycle = 1; // Virtual bus with cycle 1
            long time = 0;  // starts at time zero.
            for (int i = 0; i < s.length; i++) {
                if (!"x".equals(s[i])) {
                    long j = Long.parseLong(s[i]);
                    time = match(time, cycle, j, i);
                    cycle = cycle * j;
                    System.out.println("New cycle is " + cycle);
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        Puzzle2 p = new Puzzle2();
        p.process(args[0]);
    }
    
}
