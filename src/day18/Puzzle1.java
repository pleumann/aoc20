package day18;

/**
 * Operation Order part 1. No operator precedence. 'term' is simply skipped.
 */
public class Puzzle1 extends Puzzle0 {

    /**
     * expr := fact { ( '*' | '+' ) fact }
     */
    @Override
    long evalExpr() {
        long result = evalFact();

        while ("*".equals(token) || "+".equals(token)) {
            if ("*".equals(token)) {
                nextToken();
                result = result * evalFact();
            } else {
                nextToken();
                result = result + evalFact();
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Puzzle1 p = new Puzzle1();
        p.process(args[0]);
    }
}
