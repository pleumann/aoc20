package day09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Encoding Error part 2.
 */
public class Puzzle2 {
    
    void process(String file, int target) {
        ArrayList<Integer> values = new ArrayList();
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            //int i = Integer.parseInt(reader.readLine());
            //values.add(i);
            int sum = 0;

            String s = reader.readLine();
            while (s != null) {
                int i = Integer.parseInt(s);

                values.add(i);
                sum = sum + i;

                while (sum > target) {
                    int j = values.get(0);
                    values.remove(0);
                    sum = sum - j;
                }
                
                if (sum == target) {
                    
                    int min = Integer.MAX_VALUE;
                    int max = Integer.MIN_VALUE;
                    
                    for (int j = 0; j < values.size(); j++) {
                        min = Math.min(min, values.get(j));
                        max = Math.max(max, values.get(j));
                    }
                    
                    System.out.println("" + min + "+" + max + "=" + (min + max));
                    return;
                }
                
                s = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Missing 2nd argument: result from 1st part");
            System.exit(1);
        }
        
        new Puzzle2().process(args[0], Integer.parseInt(args[1]));
    }
}
