package day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Tobbogan Trajectory 1.
 */
public class Puzzle1 {
    
    String[] input;
    
    void loadInput(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList<String> list = new ArrayList();

            String s = reader.readLine();
            while (s != null) {
                list.add(s);
                s = reader.readLine();
            }

            input = list.toArray(new String[list.size()]);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    int checkSlope() {
        int count = 0;
        int pos = 0;

        for (int i = 0; i < input.length; i++) {
            if (input[i].charAt(pos) == '#') {
                count++;
            }

            pos = (pos + 3) % input[i].length();
        }
        
        return count;
    }    
    
    public static void main(String[] args) {
        Puzzle1 p = new Puzzle1();
        p.loadInput(args[0]);
        
        int i = p.checkSlope();
        
        System.out.println("Trees: " + i);
    }    
    
}
