package day21;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Allergen Assessment part 1.
 */
public class Puzzle1 {

    /**
     * Helper class for representing a single recipy.
     */
    class Recipy {

        HashSet<String> ingredients = new HashSet();

        HashSet<String> allergens = new HashSet();

    }

    /**
     * List of all recipies.
     */
    ArrayList<Recipy> recipies = new ArrayList();

    /**
     * Global list of all ingredients, modified during processing.
     */
    HashSet<String> ingredients = new HashSet();
    
    /**
     * Global list of all allergens, modified during processing.
     */
    HashSet<String> allergens = new HashSet();

    /**
     * Loads the input from the given file, builds initial data structures and
     * prints some stats.
     */
    void loadInput(String file) {
        System.out.println();
        System.out.println("Loading input...");
        System.out.println();
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String s = reader.readLine();
            while (s != null) {
                System.out.print(".");

                int p = s.indexOf("(");

                String[] left = s.substring(0, p).split(" ");
                String[] right = s.substring(p + 10, s.length() - 1).replace(" ", "").split(",");

                Recipy r = new Recipy();

                for (String t : left) {
                    r.ingredients.add(t);
                    ingredients.add(t);
                }

                for (String t : right) {
                    r.allergens.add(t);
                    allergens.add(t);
                }
                
                recipies.add(r);

                s = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        System.out.println();
        System.out.println();
        System.out.println("Total recipies:    " + recipies.size());
        System.out.println("Total ingredients: " + ingredients.size());
        System.out.println("Total allergens:   " + allergens.size());
        System.out.println();
    }
    
    /**
     * Returns the intersection of two sets.
     */
    HashSet<String> and(HashSet<String> a, HashSet<String> b) {
        HashSet<String> c = new HashSet(a);
        c.retainAll(b);
        return c;
    }

    /**
     * Finds and eliminates allergen/ingredient combination that can be 
     * identified uniquely. If no combination can be found return null.
     */
    String eliminate() {
        
        // Consider remaining allegens.
        for (String s: allergens) {
            // Consider remaining ingredients.
            HashSet<String> w = new HashSet(ingredients); // Need a copy here.
            
            // Consider all recipies. Build the itersection ("and") of
            // ingredients for those recipies that contain the allergen. 
            for (int j = 0; j < recipies.size(); j++) {
                Recipy r = recipies.get(j);
                if (r.allergens.contains(s)) {
                    w = and(w, r.ingredients);
                }
            }
            
            // If exactly one ingredient remains we did identify the ingredient
            // that contains the allergen. Return both after deleting them from
            // the global sets (because we don't want to consider them again).
            if (w.size() == 1) {
                String x = w.iterator().next();
                ingredients.remove(x);
                allergens.remove(s);
                
                return (s + " in " + x);
            }
        }
        
        return null;
    }

    /**
     * Solves the puzzle.
     */
    void process(String file) {
        loadInput(file);
        
        String s = eliminate();
        while (s != null) {
            System.out.println("Found " + s + ".");
            s = eliminate();
        }
        
        int count = 0;
        
        for (Recipy r: recipies) {
            count = count + and(ingredients, r.ingredients).size();
        }

        System.out.println();
        System.out.println("Harmless ingredient occurrence count: " + count);
        System.out.println();
    }

    public static void main(String[] args) {
        Puzzle1 p = new Puzzle1();
        p.process(args[0]);
    }

}
