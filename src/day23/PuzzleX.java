package day23;

/**
 * Crab Cups part 1 and 2 in a single class for benchmarking against C & Pascal.
 */
public class PuzzleX extends Puzzle1 {
    
    public static void main(String[] args) {
        System.out.println("*** AoC 2020.23 Crab Cups ***");
        System.out.println();
        
        System.out.println("--- Part 1 ---");
        System.out.println();
        
        init("398254716", 9);
        play(100);
        done();
        
        System.out.println("--- Part 2 ---");
        System.out.println();
        
        init("398254716", 1000000);
        play(10000000);
        done();
    }
}
