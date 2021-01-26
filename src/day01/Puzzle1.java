package day01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Report Repair 1. Straightforward solution with two nested loops. O(n^2).
 */
public class Puzzle1 {
    
    int[] input;

    void loadInput(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList<String> list = new ArrayList();

            String s = reader.readLine();
            while (s != null) {
                list.add(s);
                s = reader.readLine();
            }

            input = new int[list.size()];

            for (int i = 0; i < list.size(); i++) {
                input[i] = Integer.parseInt(list.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    void checkReport() {
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                int a = input[i];
                int b = input[j];

                int sum = a + b;

                if (sum == 2020) {
                    int prod = a * b;

                    System.out.println("" + a + "+" + b + "=" + sum);
                    System.out.println("" + a + "*" + b + "=" + prod);
                }
            }
        }
    }

    public static void main(String[] args) {
        Puzzle1 p = new Puzzle1();
        p.loadInput(args[0]);
        p.checkReport();
    }

}
