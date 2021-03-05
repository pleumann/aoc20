package day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Binary Boarding part 1.
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

    int doPartition(String s, int start, int count) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == 'B' || c == 'R') {
                start = start + count / 2;
            }

            count = count / 2;
        }

        return start;
    }

    int getSeat(String s) {
        int row = doPartition(s.substring(0, 7), 0, 128);
        int col = doPartition(s.substring(7, 10), 0, 8);

        int seat = row * 8 + col;

        System.out.println(s + " -> row " + row + ", col " + col + ", seat " + seat);

        return seat;
    }

    void handleBoarding() {
        int max = 0;

        for (String s : input) {
            int seat = getSeat(s);
            max = Math.max(max, seat);
        }

        System.out.println("Highest seat: " + max);
    }
    
    public static void main(String[] args) {
        Puzzle1 p = new Puzzle1();
        p.loadInput(args[0]);
        p.handleBoarding();
    }

}
