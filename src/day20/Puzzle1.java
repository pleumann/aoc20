package day20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static day20.Edge.*;

/**
 * Jurassic Jigsaw part 1.
 */
public class Puzzle1 {

    /**
     * The list of available tiles as loaded from the input file.
     */
    ArrayList<Tile> tiles = new ArrayList();
    
    /**
     * The size (= width = height) of the overall image in tiles.
     */
    int size;

    /**
     * A 2D array of tiles we use to assemble the image.
     */
    Tile[][] image;
    
    /**
     * Loads tiles from the input file.
     */
    void readInput(String file) {
        try {
            System.out.println("Reading input file " + file + "...\n");
            
            BufferedReader reader = new BufferedReader(new FileReader(file));

            char[][] data = new char[10][];

            String s = reader.readLine();
            while (s != null) {
                int number = Integer.parseInt(s.substring(5, 9));

                for (int i = 0; i < 10; i++) {
                    data[i] = reader.readLine().toCharArray();
                }

                tiles.add(new Tile(number, data));

                s = reader.readLine();
                s = reader.readLine();
            }

            size = (int)Math.sqrt(tiles.size());
            
            System.out.println("Done.\n\nGrid size is " + size + "x" + size + " tiles.\n");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Finds the upper left corner of the image from a given list of tiles.
     */
    Tile findUpperLeft(ArrayList<Tile> tiles) {
        System.out.println("Finding upper end of image...\n");

        Tile t = tiles.get(0);
        tiles.remove(t);
        t.dump();

        Tile u = t.match(NORTH, tiles);
        while (u != null) {
            t = u;
            u = t.match(NORTH, tiles);
        }

        System.out.println("Done.\n\nFinding left end of image...\n");

        u = t.match(WEST, tiles);
        while (u != null) {
            t = u;
            u = t.match(WEST, tiles);
        }

        return t;
    }

    /**
     * Assembles the whole image from a starting piece representing the upper
     * left corner and a list of available pieces.
     */    
    void assembleImage(Tile t, ArrayList<Tile> tiles) {
        System.out.println("Upper left corner identified. Assembling image...\n");

        image = new Tile[size][size];

        for (int i = 0; i < size; i++) {
            image[i][0] = t;

            for (int j = 1; j < size; j++) {
                image[i][j] = image[i][j-1].match(EAST, tiles);
            }

            t = t.match(SOUTH, tiles);
        }

        System.out.println("Done.\n");
    }
        
    /**
     * Solves the puzzle for the given input.
     */
    void process(String file) {
        readInput(file);
        
        Tile t = findUpperLeft(new ArrayList<Tile>(tiles)); // We need a copy here.
        tiles.remove(t);
        assembleImage(t, tiles);
        assert(tiles.isEmpty());

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < size; k++) {
                    System.out.print(new String(image[i][k].image[j]) + " ");
                }
                System.out.println();
            }
            System.out.println();
        }

        long ul = image[0][0].number;
        long ur = image[size-1][0].number;
        long ll = image[0][size-1].number;
        long lr = image[size-1][size-1].number;

        System.out.println("Upper left corner is " + ul);
        System.out.println("Upper right corner is " + ur);
        System.out.println("Lower left corner is " + ll);
        System.out.println("Lower right corner is " + lr);
        System.out.println();
        System.out.println("Product is " + ul * ur * ll * lr);
        System.out.println();
    }

    public static void main(String[] args) {
        Puzzle1 p = new Puzzle1();
        p.process(args[0]);
    }

}
