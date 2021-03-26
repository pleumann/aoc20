package day10;

import java.util.HashMap;

/**
 * Adapter Array part 2.
 */
public class Puzzle2Old extends Puzzle1Old {

    HashMap<Integer, Long> cache = new HashMap();

    long solve(int jolts, int target) {
        if (jolts == target) {
            return 1;
        }
        
        if (cache.containsKey(jolts)) {
            return cache.get(jolts);
        }

        long ways = 0;
        
        if (adapters.contains(jolts + 1)) {
            ways = ways + solve(jolts + 1, target);
        }

        if (adapters.contains(jolts + 2)) {
            ways = ways + solve(jolts + 2, target);
        }

        if (adapters.contains(jolts + 3)) {
            ways = ways + solve(jolts + 3, target);
        }
        
        cache.put(jolts, ways);
        
        return ways;
    }

    public static void main(String[] args) {
        Puzzle2Old p = new Puzzle2Old();
        p.load(args[0]);
        System.out.println("ways = " + p.solve(0, p.max));
    }

    
}
