package day01;

/**
 * Report Repair 2. Straightforward solution with three nested loops. O(n^3).
 */
public class Puzzle2 extends Puzzle1 {

    // Note: loadInput() method is inherited from Puzzle1.
    
    @Override
    void checkReport() {
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                for (int k = j + 1; k < input.length; k++) {
                    int a = input[i];
                    int b = input[j];
                    int c = input[k];

                    int sum = a + b + c;

                    if (sum == 2020) {
                        int prod = a * b * c;

                        System.out.println("" + a + "+" + b + "+" + c + "=" + sum);
                        System.out.println("" + a + "*" + b + "*" + c + "=" + prod);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Puzzle2 p = new Puzzle2();
        p.loadInput(args[0]);
        p.checkReport();
    }

}
