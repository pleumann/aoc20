package day14;

/**
 * Docking Data part 1. Straightforward solution using HashMap for simulating
 * memory.
 */
public class Puzzle2 extends Puzzle1 {
    
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
            memory.put(address & mask0 | mask1 | applyBits(maskX, i), value);
        }
    }

    public static void main(String[] args) {
        Puzzle2 p = new Puzzle2();
        p.process(args[0]);
    }
    
}
