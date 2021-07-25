package day20;

/**
 * Jurassic Jigsaw part 2.
 */
public class Puzzle2 extends Puzzle1 {
    
    @Override
    void process(String file) {
        super.process(file);

        System.out.println("Glueing parts together...\n");
        
        char[][] complete = new char[size * 8][size * 8];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < size; k++) {
                    System.arraycopy(image[i][k].image[j + 1], 1, complete[i * 8 + j], k * 8, 8);
                }
            }
        }

        Tile sea = new Tile(9999, complete);

        System.out.println("Done.\n\nFlipping and rotating image...\n");
        
        sea.flip();
        sea.rotate();
        //sea.rotate();
        //sea.rotate();

        sea.dump();

        char[][] nessy = new char[][] {
                "                  # ".toCharArray(),
                "#    ##    ##    ###".toCharArray(),
                " #  #  #  #  #  #   ".toCharArray()
        };

        System.out.println("Done.\n\nFinding and marking sea monsters...\n");
        
        for (int i = 0; i < sea.image.length - nessy.length; i++) {
            for (int j = 0; j < sea.image[0].length - nessy[0].length; j++) {
                if (sea.look(i, j, nessy)) {
                    sea.mark(i, j, nessy);
                }
            }
        }

        System.out.println("Done.");
        System.out.println();
        
        sea.dump();

        System.out.println("Roughness=" + sea.getRoughness());
        System.out.println();
    }

    public static void main(String[] args) {
        Puzzle2 p = new Puzzle2();
        p.process(args[0]);
    }

}
