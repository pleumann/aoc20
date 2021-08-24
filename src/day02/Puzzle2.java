package day02;

/**
 * Password Philosophy 2.
 */
public class Puzzle2 extends Puzzle1 {
    
    // Note: process() method inherited from Puzzle1.
    
    @Override
    boolean isValid(String password) {
        int dash = password.indexOf('-');
        int colon = password.indexOf(':');
        
        int min = Integer.parseInt(password.substring(0, dash));
        int max = Integer.parseInt(password.substring(dash + 1, colon - 2));
        
        char chr = password.charAt(colon - 1); 
        
        return (password.charAt(colon + min + 1) == chr) ^ (password.charAt(colon + max + 1) == chr);
    }
    
    public static void main(String[] args) {
        Puzzle2 p = new Puzzle2();
        p.process(args[0]);
    }
}
