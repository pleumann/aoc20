package day08;

/**
 * Handheld Halting part 1.
 */
public class Puzzle2 extends Puzzle1 {

    public static void main(String[] args) {
        Puzzle2 p = new Puzzle2();
        p.loadInput(args[0]);
        
        /*
         * The lazy way to solve the puzzle. Try to change a "jmp" or "nop"
         * instruction in the program into "nop" or "jmp" (respectively) and
         * run the program. Wash, rinse, repeat until things terminate.
         */
        for (int i = 0; i < p.input.length; i++) {
            if (p.input[i].startsWith("jmp")) {
                p.input[i] = "nop" + p.input[i].substring(3);
            } else if (p.input[i].startsWith("nop")) {
                p.input[i] = "jmp" + p.input[i].substring(3);
            }
            
            if (p.runProgram()) {
                break;
            }
            
            p.loadInput(args[0]); // Restore program
        }
    }
}
