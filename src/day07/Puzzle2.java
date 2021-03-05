package day07;

/**
 * Handy Haversacks part 2.
 */
public class Puzzle2 extends Puzzle1 {

    void solvePart2() {
        Bag gold = getBag("shiny gold");
        int count = gold.size() - 1;

        System.out.println(count);
    }
            
    public static void main(String[] args) {
        Puzzle2 p = new Puzzle2();
        p.loadInput(args[0]);
        p.solvePart2();
    }    

}
