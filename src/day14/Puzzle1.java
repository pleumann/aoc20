package day14;

import java.util.HashMap;
import util.Helper;

/**
 * Docking Data part 1. Straightforward solution using HashMap for simulating
 * memory.
 */
public class Puzzle1 {
    
    long mask0 = 0;
    long mask1 = 0;
    long maskX = 0;

    HashMap<Long,Long> memory = new HashMap();

    static long getBits(String mask, char c) {
        long result = 0;
        
        for (int i = 0; i < mask.length(); i++) {
            result = result << 1;
            if (mask.charAt(i) == c) {
                result = result | 1;
            }
        }
        
        return result;
    }

    void poke(long address, long value) {
        memory.put(address, value & ~mask0 | mask1);
    }

    void process(String file) {
        String[] instructions = Helper.loadStrings(file);

        for (String s : instructions) {
            if (s.startsWith("mask = ")) {
                String t = s.substring(7);

                mask0 = getBits(t, '0');
                mask1 = getBits(t, '1');
                maskX = getBits(t, 'X');
            } else if (s.startsWith("mem[")) {
                int p = s.indexOf(']');
                long address = Long.parseLong(s.substring(4, p));
                long value = Long.parseLong(s.substring(p + 4));

                poke(address, value);
            }
            
            System.out.print('.');
        }
        
        long result = 0;
        for (Long l: memory.values()) {
            result = result + l;
        }

        System.out.println("\n\nResult=" + result + " (used addresses=" + memory.size() + ")");
    }

    public static void main(String[] args) {
        Puzzle1 p = new Puzzle1();
        p.process(args[0]);
    }
    
}
