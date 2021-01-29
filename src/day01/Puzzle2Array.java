package day01;

/**
 * Report Repair 2.  Refined solution with boolean array. O(n^2).
 */
public class Puzzle2Array extends Puzzle1 {

    // Note: loadInput() method is inherited from Puzzle1.
    
    @Override
    void checkReport() {
        boolean[] seen = new boolean[2020];

        for (int i = 0; i < input.length; i++) {
            seen[input[i]] = true;
        }
        
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                int a = input[i];
                int b = input[j];
                int c = 2020 - (a + b);

                if (c >= 0 && seen[c]) {
                    int prod = a * b * c;

                    System.out.println("" + a + "+" + b + "+" + c + "=" + 2020);
                    System.out.println("" + a + "*" + b + "*" + c + "=" + prod);
                }
            }
        }
    }

    public static void main(String[] args) {
        Puzzle2Array p = new Puzzle2Array();
        p.loadInput(args[0]);
        p.checkReport();
    }

}
