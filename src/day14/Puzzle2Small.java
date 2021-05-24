package day14;

/**
 * Docking Data part 2. Space-optimized solution using just a HashSet and a long
 * to keep track of used memory addresses and sum of values, respectively. For
 * this to work we need process the instructions in reverse order. 
 */
public class Puzzle2Small extends Puzzle1Small {   

    static long applyBits(long mask, long bits) {
        long result = 0;
        int position = 0;
        
        while (mask != 0) {
            if ((mask & 1) == 1) {
                result = result | ((bits & 1) << position);
                bits = bits >> 1;
            }
            
            position++;
            mask = mask >> 1;
        }
        
        return result;
    }
    
    @Override
    void poke(long address, long value) {
        int combs = 1 << Long.bitCount(maskX);
        for (int i = 0; i < combs; i++) {
            poke0(address & mask0 | mask1 | applyBits(maskX, i), value);
        }
    }

    public static void main(String[] args) {
        Puzzle2Small p = new Puzzle2Small();
        p.process(args[0]);
    }
    
}
