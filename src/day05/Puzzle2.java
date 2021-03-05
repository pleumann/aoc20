package day05;

/**
 * Binary Boarding part 2.
 */
public class Puzzle2 extends Puzzle1 {

    @Override
    void handleBoarding() {
        boolean[] status = new boolean[1024];

        for (String s : input) {
            int seat = getSeat(s);
            status[seat] = true;
        }

        for (int i = 1; i < 1022; i++) {
            if (status[i - 1] && !status[i] && status[i + 1]) {
                System.out.println("Your seat: " + i);
            }
        }
    }
    
    public static void main(String[] args) {
        Puzzle2 p = new Puzzle2();
        p.loadInput(args[0]);
        p.handleBoarding();
    }

}
