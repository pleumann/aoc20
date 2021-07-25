package day20;

import static day20.Edge.*;
import java.util.ArrayList;

/**
 * Represents a tile of the image or the whole image. Tiles are square. They
 * support all the basic operations that we need for solving the puzzle, i.e.
 * flipping, rotating, matching etc.
 */
class Tile {

    /**
     * The number of our tile.
     */
    int number;

    /**
     * The size of our tile.
     */
    int size;
    
    /**
     * The actual content as a 2D character array.
     */
    char[][] image;

    /**
     * Constructs a new tile with the given number and content.
     */
    public Tile(int number, char[][] image) {
        this.number = number;
        
        size = image.length;
        
        this.image = new char[size][];

        for (int i = 0; i < image.length; i++) {
            this.image[i] = image[i].clone();
            assert(image[i].length == size); // Better safe than sorry.
        }
    }

    /**
     * Rotates the tile by 90 degrees clockwise.
     */
    void rotate() {
        char[][] result = new char[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[j][size - i - 1] = image[i][j];
            }
        }

        image = result;
    }

    /**
     * Flips (mirrors) the tile vertically.
     */
    void flip() {
        char[][] result = new char[size][];

        for (int i = 0; i < size; i++) {
            result[i] = image[size - i - 1];
        }

        image = result;
    }

    /**
     * Returns a string reflecting an edge of the tile.
     */
    String getBorder(Edge edge) {
        switch (edge) {
            case NORTH: {
                return new String(image[0]);
            }
            case SOUTH: {
                return new String(image[size - 1]);
            }   
            case EAST: {
                char[] result = new char[size];
                for (int i = 0; i < size; i++) {
                    result[i] = image[i][size - 1];
                }
                return new String(result);
            }
            case WEST: {
                char[] result = new char[size];
                for (int i = 0; i < size; i++) {
                    result[i] = image[i][0];
                }
                return new String(result);
            }
            default: {
                throw new IllegalArgumentException("Not a direction: " + edge);
            }
        }
    }
    
    /**
     * Searches a list of available tiles for one that matches this tile at the
     * given edge. Flips and rotates as needed. Returns the matching tile (and
     * removes it from the list) if one is found. Returns null otherwise.
     */
    Tile match(Edge edge, ArrayList<Tile> tiles) {
        String value = getBorder(edge);

        System.out.println("Searching match for " + value + " to the "  + edge + "...");
        System.out.println();

        switch (edge) {
            case NORTH: edge = SOUTH; break;
            case SOUTH: edge = NORTH; break;
            case EAST:  edge = WEST;  break;
            case WEST:  edge = EAST;  break;
        }
        
        for (Tile t: tiles) {
            for (int i = 0; i < 2; i++) {
                t.flip();
                
                for (int j = 0; j < 4; j++) {
                    t.rotate();
                    if (t.getBorder(edge).equals(value)) {
                        t.dump();
                        tiles.remove(t);
                        return t;
                    }
                }
            }
        }
        
        System.out.println("No tile found!");
        System.out.println();
        
        return null;
    }

    /**
     * Prints the tile to the screen, for debugging.
     */
    public void dump() {
        System.out.println("-< " + number + " >-");
        for (char[] a: image) {
            System.out.println(new String(a));
        }
        System.out.println();
    }

    /**
     * Checks if the tile contains a monster at the given position.
     */
    public boolean look(int x, int y, char[][] monster) {
        for (int i = 0; i < monster.length; i++) {
            for (int j = 0; j < monster[i].length; j++) {
                char c = monster[i][j];
                char d = image[x + i][y + j];
                
                if (c == '#' && d == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Marks a monster at the given position.
     */
    public boolean mark(int x, int y, char[][] monster) {
        for (int i = 0; i < monster.length; i++) {
            for (int j = 0; j < monster[i].length; j++) {
                char c = monster[i][j];
                
                if (c == '#') {
                    image[x + i][y + j] = 'O';
                }
            }
        }
        return true;
    }

    /**
     * Returns the overall roughness of the tile (i.e. the number of # chars).
     */
    public int getRoughness() {
        int count = 0;
        
        for (char[] a: image) {
            for (char c: a) {
                if (c == '#') {
                    count++;
                }
            }
        }
        
        return count;
    }    

}
