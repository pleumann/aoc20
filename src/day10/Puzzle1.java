package day10;

import java.util.Arrays;

import util.Helper;

/**
 * Adapter Array part 1.
 */
public class Puzzle1 {

    int[] adapters;
        
    void load(String file) {
        adapters = Helper.loadIntArray(file);
        Arrays.sort(adapters, 0, adapters.length);
    }
    
    long solve() {
        int last = 0;       // Joltage of previous adapter
        
        int oneCount = 0;   // Counter for "one" steps
        int twoCount = 0;   // Counter for "two" steps (not actually needed)
        int threeCount = 1; // Counter for "three" steps

        for (int jolts: adapters) {
            System.out.println("jolts=" + jolts + " last=" + last);
            
            // We want to use all adapters, so prefer smaller steps
            if (jolts == last + 1) {
                oneCount++;
            } else if (jolts == last + 2) {
                twoCount++;
            } else if (jolts == last + 3) {
                threeCount++;
            } else {
                System.out.println("Unsolvable!");
                System.exit(1);
            }

            last = jolts;
        }
        
        return oneCount * threeCount;
    }

    public static void main(String[] args) {
        Puzzle1 p = new Puzzle1();
        p.load(args[0]);
        System.out.println("ones * threes = " + p.solve());
    }

    
}
