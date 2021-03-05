package day06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Custom Customs part 1.
 */
public class Puzzle1 {

    /**
     * Returns an int with a bit representing each letter a-z.
     */
    int getBits(String s) {
        int bits = 0;
        for (int i = 0; i < s.length(); i++) {
            bits = bits | (1 << (s.charAt(i) - 'a'));
        }
        return bits;
    }

    /**
     * Counts the 1 bits in an int. Same as Integer.bitCount().
     */
    int countBits(int bits) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((bits & (1 << i)) != 0) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Merges two bits representing answers. 'or' here, 'and' in part 2.
     */
    int mergeBits(int i, int j) {
        return i | j;
    }
    
    void process(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            int answer = 0;

            String s = reader.readLine();
            while (s != null) {
                int i = getBits(s);
                s = reader.readLine();

                while (s != null && !"".equals(s)) {
                    i = mergeBits(i, getBits(s));
                    s = reader.readLine();
                }
                
                int j = countBits(i);
                
                System.out.println(j);

                answer = answer + j;
                
                if ("".equals(s)) {
                    s = reader.readLine();
                }
            }
            
            System.out.println("Answer: " + answer);
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
