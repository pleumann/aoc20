package day02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Password Philosophy 1.
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

    boolean isValid(String password) {
        int dash = password.indexOf('-');
        int colon = password.indexOf(':');

        int min = Integer.parseInt(password.substring(0, dash));
        int max = Integer.parseInt(password.substring(dash + 1, colon - 2));

        char chr = password.charAt(colon - 1);

        int count = 0;
        for (int i = colon + 1; i < password.length(); i++) {
            if (password.charAt(i) == chr) {
                count++;
            }
        }

        return (min <= count) && (count <= max);
    }

    void checkPasswords() {
        int count = 0;

        for (String s: input) {
            if (isValid(s)) {
                count++;
            }
        }

        System.out.println("Valid passwords: " + count);
    }

    public static void main(String[] args) {
        Puzzle1 p = new Puzzle1();
        p.loadInput(args[0]);
        p.checkPasswords();
    }
}
