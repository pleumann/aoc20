package day23;

/**
 * Crab Cups part 1. My original quick and dirty solution using a string for
 * storing the order of cups. Worked, but was obviously not suitable for part 2,
 * so I implemented a new one. This one's here only as a "historical reference".
 * And also as an example of how much your jaw can drop when you get to read
 * part 2. :-)
 */
public class Puzzle0 {
 
    String cups = "398254716";
    
    int current;
    
    String hand;
    
    int round;
    
    void pick() {
        hand = cups.substring(1, 4);
        cups = cups.substring(0, 1) + cups.substring(4);
        
        System.out.println("hand=" + hand);
    }
    
    void place() {
        char c = cups.charAt(0);
        do {
            c--;
            if (c == '0') {
                c = '9';
            }
        } while (hand.indexOf(c) != -1);
        
        int dest = cups.indexOf(c);
        
        System.out.println("dest=" + c + " (at " + dest + ")");
        
        cups = cups.substring(0, dest + 1) + hand + cups.substring(dest + 1);
    }

    void rotate() {
        cups = cups.substring(1) + cups.charAt(0);
    }
    
    void round() {
        pick();
        place();
        rotate();
        
        round++;
        
        System.out.println(round);
        System.out.println(cups);
    }
    
    public static void main(String[] args) {
        Puzzle0 p = new Puzzle0();
        for (int i = 0; i < 100; i++) {
            p.round();
        }
    }
}
