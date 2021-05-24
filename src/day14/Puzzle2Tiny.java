package day14;

/**
 * Docking Data part 2. Further optimized solution that keeps track of used
 * memory address patterns and sum of values. Uses very little memory at the
 * cost of longer runtime (because we need to check each memory write against
 * a list of address patterns now).
 */
public class Puzzle2Tiny extends Puzzle1Tiny {
    
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
            long realAddress = address & mask0 | mask1 | applyBits(maskX, i); 
            poke0(realAddress, value);
        }
        
        memAddr[memCount] = address & mask0 | mask1;
        memMask[memCount] = maskX;
        memCount++;
    }
    
    public static void main(String[] args) {
        Puzzle2Tiny p = new Puzzle2Tiny();
        p.process(args[0]);
    }
    
}
