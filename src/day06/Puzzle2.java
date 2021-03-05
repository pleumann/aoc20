package day06;

/**
 * Custom Customs part 2.
 */
public class Puzzle2 extends Puzzle1 {
    
    @Override
    int mergeBits(int i, int j) {
        return i & j;
    }

    public static void main(String[] args) {
        Puzzle2 p = new Puzzle2();
        p.process(args[0]);
    }
    
}
