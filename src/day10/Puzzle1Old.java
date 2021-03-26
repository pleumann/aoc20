package day10;

import java.util.HashSet;

import util.Helper;

/**
 * Adapter Array part 1.
 */
public class Puzzle1Old {

    HashSet<Integer> adapters = new HashSet();
    
    int max;
    
    void load(String file) {
        int[] data = Helper.loadIntArray(file);

        for (int i: data) {
            max = Math.max(max, i);
            adapters.add(i);
        }
    }
    
    long solve() {
        int jolts = 0;
        int ones = 0;
        int threes = 1;

        while (!adapters.isEmpty()) {
            System.out.println(jolts);
            if (adapters.contains(jolts + 1)) {
                jolts = jolts + 1;
                ones++;
            } else if (adapters.contains(jolts + 2)) {
                jolts = jolts + 2;
            } else if (adapters.contains(jolts + 3)) {
                jolts = jolts + 3;
                threes++;
            } else {
                System.out.println("Unsolvable!");
                System.exit(1);
            }
            adapters.remove(jolts);
        }
        
        return ones * threes;
    }

    public static void main(String[] args) {
        Puzzle1Old p = new Puzzle1Old();
        p.load(args[0]);
        System.out.println("ones * threes = " + p.solve());
    }

    
}
