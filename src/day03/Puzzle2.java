package day03;

/**
 * Tobbogan Trajectory 2. Basically a generalized variant of part 1.
 */
public class Puzzle2 extends Puzzle1 {
    
    // Note: loadInput() method inherited from Puzzle 1.
    
    int checkSlope(int right, int down) {
        int count = 0;
        int pos = 0;

        for (int i = 0; i < input.length; i = i + down) {
            if (input[i].charAt(pos) == '#') {
                count++;
            }

            pos = (pos + right) % input[i].length();
        }
        
        return count;
    }    

    int checkSlope() {
        return checkSlope(3, 1);
    }
    
    public static void main(String[] args) {
        Puzzle2 p = new Puzzle2();
        p.loadInput(args[0]);
        
        long l = 1; // Note: Need long here to avoid overflow.
        
        l = l * p.checkSlope(1, 1);
        l = l * p.checkSlope(3, 1);
        l = l * p.checkSlope(5, 1);
        l = l * p.checkSlope(7, 1);
        l = l * p.checkSlope(1, 2);
        
        System.out.println("Product of trees: " + l);
    }    
}
