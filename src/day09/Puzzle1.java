package day09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Encoding Error part 1.
 */
public class Puzzle1 {
    
    /**
     * Checks new number against array of preceding numbers. Shifts array and
     * inserts new number on success. Return value reflects success.
     */
    boolean check(int[] values, int i) {
        for (int j = 0; j < values.length; j++) {
            for (int k = j + 1; k < values.length; k++) {
                if (values[j] + values[k] == i) {
                    // Bit ugly, but we don't care.
                    System.arraycopy(values, 1, values, 0, values.length - 1);
                    values[values.length - 1] = i;
                    return true;
                }
            }
        }
        return false;
    }
    
    void process(String file, int preamble) {
        int[] values = new int[preamble];
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            for (int i = 0; i < preamble; i++) {
                values[i] = Integer.parseInt(reader.readLine());
            }
            
            String s = reader.readLine();
            while (s != null) {
                int i = Integer.parseInt(s);

                if (!check(values, i)) {
                    System.out.println(i);
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
            System.err.println("Missing 2nd argument: preamble");
            System.exit(1);
        }
        
        new Puzzle1().process(args[0], Integer.parseInt(args[1]));
    }
}
