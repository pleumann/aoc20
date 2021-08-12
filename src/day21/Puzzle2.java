package day21;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Allergen Assessment part 2.
 */
public class Puzzle2 extends Puzzle1 {
    
    @Override
    void process(String file) {
        loadInput(file);
        
        ArrayList<String> found = new ArrayList();
        
        String s = eliminate();
        while (s != null) {
            System.out.println("Found " + s + ".");
            found.add(s);
            s = eliminate();
        }
          
        Collections.sort(found);

        String list = "";
        for (String t: found) {
            int p = t.indexOf(" in ");
            list = list + (list.isEmpty() ? "" : ",") + t.substring(p + 4);
        }
        
        System.out.println();
        System.out.println("Canonical dangerous ingredient list: " + list);
        System.out.println();
    }

    public static void main(String[] args) {
        Puzzle2 p = new Puzzle2();
        p.process(args[0]);
    }

}
