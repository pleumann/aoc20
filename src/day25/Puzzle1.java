package day25;

/**
 * Combo Breaker part 1 (there is no part 2). Straightforward implementation of
 * the rules laid out in the puzzle.
 */
public class Puzzle1 {
    
    long forwardTransform(long number, int loop) {
        number = number % 20201227;
        long value = 1;
        while (loop-- > 0) {
            value = (value * number) % 20201227;
        }
        return value;
    }

    int reverseTransform(long number, long value) {
        number = number % 20201227;
        value = value % 20201227;
        long temp = 1;
        int loop = 0;
        while (temp != value) {
            loop++;
            temp = (temp * number) % 20201227;
        }
        return loop;
    }

    void hack(long input1, long input2) {
        int loop1 = reverseTransform(7, input1);
        int loop2 = reverseTransform(7, input2);
        
        System.out.println("Loop1=" + loop1);
        System.out.println("Loop2=" + loop2);
        
        System.out.println("Key=" + forwardTransform(input1, loop2));
        System.out.println("Key=" + forwardTransform(input2, loop1));
    }
    
    public static void main(String[] args) {
        Puzzle1 p = new Puzzle1();
        p.hack(5764801, 17807724);
        p.hack(11562782, 18108497);
    }
}

