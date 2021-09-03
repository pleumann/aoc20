package day24;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

/**
 * Lobby Layout part 1.
 */
public class Puzzle1 {
    
    /**
     * Contains the black floor tiles. Every tile that's not in this set is
     * white.
     */
    HashSet<String> tiles = new HashSet();
   
    /**
     * Generates a set element for the given coordinate pair (just a String).
     */
    String key(int x, int y) {
        return "" + x + "/" + y;
    }

    /**
     * Sets one initial floor tile from the given string, starting at position
     * 0/0 and taking into account the existing tiles.
     */
    void setup(String s) {
        int i = 0;
        int x = 0;
        int y = 0;
        
        while (i < s.length()) {
            char c = s.charAt(i++);
           
            switch (c) {
                case 'w':
                    x = x - 1;
                    break;
                case 'e':
                    x = x + 1;
                    break;
                case 'n':
                    y = y - 1;
                    c = s.charAt(i++);
                    if (c == 'w') {
                        x = x - 1;
                    } else {
                        x = x + 0;
                    }   break;
                case 's':
                    y = y + 1;
                    c = s.charAt(i++);
                    if (c == 'w') {
                        x = x - 0;
                    } else {
                        x = x + 1;
                    }   break;
                default:
                    break;
            }
        }
        
        String t = key(x, y);
        if (tiles.contains(t)) {
            System.out.printf("%-43s -> %3d/%3d WHITE\n", s, x, y);
            tiles.remove(t);
        } else {
            System.out.printf("%-43s -> %3d/%3d BLACK\n", s, x, y);
            tiles.add(t);
        }
    }
    
    /**
     * Processes the given input file.
     */
    void process(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            reader.lines().forEach(this::setup);
            System.out.println();
            System.out.printf("Day %3d = %4d\n", 0, tiles.size());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("*** AoC 2020.24 Lobby Layout (Part 1) ***\n");
        Puzzle1 p = new Puzzle1();
        p.process(args[0]);
    }
    
}
