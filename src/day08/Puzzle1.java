package day08;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Handheld Halting part 1.
 */
public class Puzzle1 {

    String[] input;
    
    void loadInput(String file) {
        try {
            List<String> list = Files.readAllLines(new File(file).toPath());
            input = list.toArray(new String[list.size()]);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    /**
     * Implements a simple virtual machine that runs the loaded program. Each
     * executed instruction is replaced by "hlt" as a marker that we've been
     * here before. Returns true for normal termination, false for infinite
     * loop.
     */
    boolean runProgram() {
        int pc = 0; // Program counter
        int a = 0;  // Accumulator
        
        while (pc != input.length) {
            String op = input[pc];
            input[pc] = "hlt"; // Been here before, halt the machine.
            
            System.out.println("pc=" + pc + " a=" + a + " op=" + op);
            
            if (op.startsWith("acc")) {
                a = a + Integer.parseInt(op.substring(4));
                pc++;
            } else if (op.startsWith("jmp")) {
                pc = pc + Integer.parseInt(op.substring(4));
            } else if (op.startsWith("nop")) {
                pc++;
            } else /* must be "hlt" */ {                
                System.out.println("Infinite loop detected");
                return false;
            }
        }
        
        System.out.println("Program completed normally.");
        return true;
    }

    public static void main(String[] args) {
        Puzzle1 p = new Puzzle1();
        p.loadInput(args[0]);
        p.runProgram();
    }
}
