package day11;

/**
 * Seating System part 2. Neighbour counting is "line of sight" now.
 */
public class Puzzle2 extends Puzzle1 {
    
    public Puzzle2() {
        threshold = 5;
    }
    
    @Override
    int count(int x, int y) {
        int result = 0;
        
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i != 0 || j != 0) {
                    int p = x;
                    int q = y;
                    char c;
                    do {
                        p = p + i;
                        q = q + j;
                        c = get(p, q);
                    } while (c == '.'); // Repeat for floor elements
                    
                    if (c == '#') {
                        result++;
                    }
                }
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        Puzzle2 p = new Puzzle2();
        p.loadInput(args[0]);
        
        int i = 0;
        while (p.think()) { 
            p.print();
            i++; 
        }
    }
    
}
