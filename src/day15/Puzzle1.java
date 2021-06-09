package day15;

/**
 * Rambunctious Recitation part 1 (and two, actually).
 */
public class Puzzle1 {

    /**
     * Current turn.
     */
    int turn;
    
    /**
     * Current number.
     */
    int number;

    /**
     * Last time we spoke each number (not the complete sequence, no dupes).
     */
    int[] prev;

    /**
     * Speaks 'count' turns of the sequence beginning with the 'start' numbers.
     */
    void speak(int count, int ... start) {
        prev = new int[count];
        
        for (int i = 0; i < start.length - 1; i++) {
            number = start[i];
            prev[number] = i + 1;
        }
                
        turn = start.length;
        number = start[start.length - 1];
        
        while (turn < count) {
            int next;
            if (prev[number] == 0) {
                next = 0;
            } else {
                next = turn - prev[number];
            }
            
            prev[number] = turn;
            number = next;

            turn++;
        }
        
        System.out.println("turn=" + turn + " number=" + number);     
    
    }
    
    public static void main(String [] args) {
        new Puzzle1().speak(2020, 17, 1, 3, 16, 19, 0);
    }
    
}
