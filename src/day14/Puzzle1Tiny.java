package day14;

import util.Helper;

/**
 * Docking Data part 1. Further optimized solution that keeps track of used
 * memory address patterns and sum of values. Uses very little memory at the
 * cost of longer runtime (because we need to check each memory write against
 * a list of address patterns now).
 */
public class Puzzle1Tiny {

    long mask0 = 0;
    long mask1 = 0;
    long maskX = 0;
    
    long[] memAddr = new long[512];
    long[] memMask = new long[512];
    int memCount;

    long result = 0;
    
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
        
    final void poke0(long address, long value) {
        for (int i = 0; i < memCount; i++) {
            if ((address & ~memMask[i]) == (memAddr[i] & ~memMask[i])) {
                 return;
            }
        }
        
        result += value;
    }

    void poke(long address, long value) {
        poke0(address, value & ~mask0 | mask1);
        
        memAddr[memCount] = address;
        memMask[memCount] = 0;
        memCount++;
    }
    
    
    void process(String file) {
        String[] instructions = Helper.loadStrings(file);

        for (int i = 1; i < instructions.length; i++) {
            if (!instructions[i].startsWith("mask")) {
                String s = instructions[i - 1];
                instructions[i - 1] = instructions[i];
                instructions[i] = s;
            }
        }

        for (int i = instructions.length - 1; i >= 0; i--) {
            String s = instructions[i];
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

        System.out.println("\n\nResult=" + result);
    }

    public static void main(String[] args) {
        Puzzle1Tiny p = new Puzzle1Tiny();
        p.process(args[0]);
    }
    
}
