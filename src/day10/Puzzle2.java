package day10;

/**
 * Adapter Array part 2.
 */
public class Puzzle2 extends Puzzle1 {

    long solve2() {
        int oneJolts = 0;      // Joltage of previous adapter we looked at 
        int twoJolts = 999;    // Joltage of adapter we looked at before "one"
        int threeJolts = 999;  // Joltage of adapter we looked at before "two"

        long oneWays = 1;      // Possible ways to build chain up to "one"
        long twoWays = 1;      // Possible ways to build chain up to "two"
        long threeWays = 1;    // Possible ways to build chain up to "three"
        
        for (int jolts: adapters) {
            long ways = 0;

            System.out.println("jolts=" + jolts + " " + oneJolts + "/" + twoJolts + "/" + threeJolts);
            
            // Look at ways of extending chains leading to three previous adapters.
            if (jolts - 1 == oneJolts || jolts - 2 == oneJolts || jolts - 3 == oneJolts) {
                ways = ways + oneWays;
            }
            if (jolts - 2 == twoJolts || jolts - 3 == twoJolts) {
                ways = ways + twoWays;
            }
            if (jolts - 3 == threeJolts) {
                ways = ways + threeWays;
            }

            // Adjust our "sliding window"
            threeJolts = twoJolts; twoJolts = oneJolts; oneJolts = jolts;
            threeWays = twoWays; twoWays = oneWays; oneWays = ways;
        }
        
        return oneWays;
    }

    public static void main(String[] args) {
        Puzzle2 p = new Puzzle2();
        p.load(args[0]);
        System.out.println("ways = " + p.solve2());
    }
    
}
