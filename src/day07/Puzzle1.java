package day07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Handy Haversacks part 1.
 */
public class Puzzle1 {

    HashMap<String, Bag> byName = new HashMap();
    
    Bag getBag(String s) {
        Bag b = byName.get(s);
        
        if (b == null) {
            b = new Bag(s);
            byName.put(s, b);
        }
        
        return b;
    }
    
    void loadInput(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String s = reader.readLine();
            while (s != null) {
                int i = s.indexOf(" bags contain ");
                Bag b = getBag(s.substring(0, i));

                if (!s.endsWith(" no other bags.")) {
                    i = i + 13;
                    while (i != -1) {
                        char c = s.charAt(i + 1);
                        int j = c - '0';
                        int k = s.indexOf(" bag", i);
                        
                        b.add(j, getBag(s.substring(i + 3, k)));
                        
                        i = s.indexOf(" ", k + 4);
                    }
                }
                
                s = reader.readLine();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    void solvePart1() {
        Bag gold = getBag("shiny gold");
        int count = 0;
        
        for (Bag b: byName.values()) {
            if (b.contains(gold)) {
                count++;
            }
        }

        System.out.println(count);
    }
    
    public static void main(String[] args) {
        Puzzle1 p = new Puzzle1();
        p.loadInput(args[0]);
        p.solvePart1();
    }    

}
