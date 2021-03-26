package day10;

/**
 * Adapter Array part 2.
 */
public class Puzzle2 extends Puzzle1 {

    long solve2() {
        int one = 0;        // Joltage of previous adapter we looked at 
        int two = 999;      // Joltage of adapter we looked at before "one"
        int three = 999;    // Joltage of adapter we looked at before "two"

        long oneWays = 1;   // Possible ways to build chain up to "one"
        long twoWays = 1;   // Possible ways to build chain up to "two"
        long threeWays = 1; // Possible ways to build chain up to "three"
        
        for (int jolts: adapters) {
            long ways = 0;

            System.out.println("jolts=" + jolts + " " + one + "/" + two + "/" + three);
            
            // Look at ways of extending chains leading to three previous adapters.
            if (jolts - 1 == one || jolts - 2 == one || jolts - 3 == one) {
                ways = ways + oneWays;
            }
            if (jolts - 2 == two || jolts - 3 == two) {
                ways = ways + twoWays;
            }
            if (jolts - 3 == three) {
                ways = ways + threeWays;
            }

            // Adjust our "gliding window"
            three = two; two = one; one = jolts;
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
