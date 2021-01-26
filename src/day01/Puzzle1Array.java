package day01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Report Repair 1. Refined solution with boolean array. O(n).
 */
public class Puzzle1Array {
    
    void process(String file) {
        boolean[] seen = new boolean[2020];
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String s = reader.readLine();
            while (s != null) {
                int a = Integer.parseInt(s);
                int b = 2020 - a;
                
                if (seen[b]) {
                    System.out.println("" + a + "+" + b + "=" + (a + b));
                    System.out.println("" + a + "*" + b + "=" + (a * b));
                    System.exit(0);
                }
                
                seen[a] = true;
                
                s = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        Puzzle1Array p = new Puzzle1Array();
        p.process(args[0]);
    }

}
