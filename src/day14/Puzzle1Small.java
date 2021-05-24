package day14;

import java.util.HashSet;
import util.Helper;

/**
 * Docking Data part 1. Space-optimized solution using just a HashSet and a long
 * to keep track of used memory addresses and sum of values, respectively. For
 * this to work we need process the instructions in reverse order. 
 */
public class Puzzle1Small {

    long mask0 = 0;
    long mask1 = 0;
    long maskX = 0;

    HashSet<Long> memory = new HashSet();

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

    void poke0(long address, long value) {
        if (memory.contains(address)) {
        } else {
            memory.add(address);
            result += value;
        }
    }

    void poke(long address, long value) {
        poke0(address, value & ~mask0 | mask1);
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

        System.out.println("\n\nResult=" + result + " (used addresses=" + memory.size() + ")");
    }

    public static void main(String[] args) {
        Puzzle1Small p = new Puzzle1Small();
        p.process(args[0]);
    }
    
}
