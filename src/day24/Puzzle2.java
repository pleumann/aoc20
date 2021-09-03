package day24;

import java.util.HashSet;

/**
 * Lobby Layout part 2 (and, implicitly, part 1).
 */
public class Puzzle2 extends Puzzle1 {
    
    /**
     * Counts the neighbours of the given floor tile, i.e. the immediately
     * adjacent floor tiles that are black (min 0, max 6).
     */
    int count(int x, int y) {
        int result = 0;
        
        if (tiles.contains(key(x - 1, y - 1))) result++; // NW
        if (tiles.contains(key(x    , y - 1))) result++; // NE
        if (tiles.contains(key(x - 1, y    ))) result++; // W
        if (tiles.contains(key(x + 1, y    ))) result++; // E
        if (tiles.contains(key(x    , y + 1))) result++; // SW
        if (tiles.contains(key(x + 1, y + 1))) result++; // SE
        
        return result;
    }

    /**
     * Performs a single day according to the overall rules of the game. Uses
     * an intermediate set of floor tiles to ensure all tiles are processed in
     * parallel before flipping.
     */
    void day() {
        HashSet<String> newTiles = new HashSet();
        
        for (int y = -75; y < 75; y++) {
            for (int x = -75; x < 75; x++) {
                int c = count(x, y);
                boolean black = tiles.contains(key(x, y));
                
                if (black && (c == 0 || c > 2)) {
                    // Turns white, aka "dies".
                } else if (!black && (c == 2)) {
                    // Turns black, aka "gets born".
                    newTiles.add(key(x, y));
                }  else if (black) {
                    // Stays as-is.
                    newTiles.add(key(x, y));
                }
            }
        }
        
        tiles = newTiles;
    }
    
    /**
     * Processes the given input file. Includes part 1.
     */
    @Override
    void process(String file) {
        super.process(file);
            
        for (int i = 1; i <= 100; i++) {
            day();
            System.out.printf("Day %3d = %4d\n", i, tiles.size());
        }
    }
    
    public static void main(String[] args) {
        System.out.println("*** AoC 2020.24 Lobby Layout (Part 2) ***\n");
        Puzzle2 p = new Puzzle2();
        p.process(args[0]);
    }
    
}
