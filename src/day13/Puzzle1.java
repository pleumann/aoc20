package day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Shuttle Search part 1.
 */
public class Puzzle1 {
    
    void process(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            int arrival = Integer.parseInt(reader.readLine());
            String[] buses = reader.readLine().split(",");

            
            int bestBus = 0;
            int bestWait = Integer.MAX_VALUE;
            
            for (String s: buses) {
                if (!"x".equals(s)) {
                    // Bus number is equal to its cycle, so our arrival time
                    // modulo bus number is the number of minutes 'into' the
                    // latest bus cycle. Hence bus - (arrival % bus) is how
                    // long we would have to wait for this bus.
                    int bus = Integer.parseInt(s);
                    int wait = bus - (arrival % bus);
                    
                    if (wait < bestWait) {
                        bestWait = wait;
                        bestBus = bus;
                    }
                }
            }
            
            System.out.println("Best bus is " + bestBus + " in " + bestWait + " minutes.");
            System.out.println("Result is " + bestBus * bestWait + ".");
            
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        Puzzle1 p = new Puzzle1();
        p.process(args[0]);
    }
    
}
