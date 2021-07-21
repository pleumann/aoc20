package day19;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Monster Messages part 1.
 */
public class Puzzle1 {

    /**
     * Holds all rules, more or less in their original form.
     */
    HashMap<String, String> rules = new HashMap();

    /**
     * Converts rule 0 (and, transitively, all other rules) into a single
     * regular expression. This is possible because we know the rules are not
     * recursive.
     */    
    String transform() {
        String s = rules.get("0");
        
        Pattern word = Pattern.compile("[0-9]+");
        Matcher match = word.matcher(s);
        while (match.find()) {
            int from = match.start();
            int to = match.end();
            
            String t = rules.get(s.substring(from, to));
            if (t.contains("|")) {
                t = "(" + t + ")";
            }
            
            s = s.substring(0, from) + t + s.substring(to);
            match = word.matcher(s);
        }        

        s = s.replace(" ", "").replace("\"", "");
        
        return s;
    }
    
    /**
     * Loads the rules from the given input and stores them into our hashmap.
     */
    void loadRules(BufferedReader reader) throws IOException {
        String s = reader.readLine();
        while (!"".equals(s)) {
            System.out.println(s);
            
            String[] a = s.split(":");
            rules.put(a[0].trim(), a[1]);

            s = reader.readLine();
        }
    }

    
   /**
    * Check the messages against a regex produced from the rules.
    */    
    void checkMessages(BufferedReader reader) throws IOException {
        String regex = transform();
        System.out.println(regex);

        Pattern p = Pattern.compile(regex);
        int count = 0;

        String s = reader.readLine();
        while (s != null) {
            System.out.println(s);
            
            if (p.matcher(s).matches()) {
                count++;
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }                
            
            s = reader.readLine();
        }
            
        System.out.println("Valid messages: " + count);
    }

    void process(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            
            loadRules(reader);
            checkMessages(reader);            
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
