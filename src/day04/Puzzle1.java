package day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Passport Processing 1.
 */
public class Puzzle1 {

    void loadInput(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            int count = 0;
            
            String s = reader.readLine();
            while (s != null) {
                String t = s;
                s = reader.readLine();

                while (s != null && !"".equals(s)) {
                    t = t + " " + s;
                    s = reader.readLine();
                }

                if (isValid(t)) {
                    count++;
                }

                if ("".equals(s)) {
                    s = reader.readLine();
                }
            }
            
            System.out.println("Valid passports: " + count);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    boolean isValid(String s) {
        if (!s.contains("byr:")) return false;
        if (!s.contains("iyr:")) return false;
        if (!s.contains("eyr:")) return false;
        if (!s.contains("hgt:")) return false;
        if (!s.contains("hcl:")) return false;
        if (!s.contains("ecl:")) return false;
        if (!s.contains("pid:")) return false;

        return true;
    }

    public static void main(String[] args) {
        Puzzle1 p = new Puzzle1();
        p.loadInput(args[0]);
    }
    
}
