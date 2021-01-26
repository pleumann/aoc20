package day01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

/**
 * Report Repair 1. Refined solution with integer hash set. O(n).
 */
public class Puzzle1Hashing {
    
    void process(String file) {
        HashSet<Integer> seen = new HashSet();
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String s = reader.readLine();
            while (s != null) {
                int a = Integer.parseInt(s);
                int b = 2020 - a;
                
                if (seen.contains(b)) {
                    System.out.println("" + a + "+" + b + "=" + (a + b));
                    System.out.println("" + a + "*" + b + "=" + (a * b));
                    System.exit(0);
                }
                
                seen.add(a);
                
                s = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        Puzzle1Hashing p = new Puzzle1Hashing();
        p.process(args[0]);
    }

}
