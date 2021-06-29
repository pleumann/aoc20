package day18;

/**
 * Operation Order part 2. Addition takes precedence over multiplication. This
 * changes two rules in our grammar.
 */
public class Puzzle2 extends Puzzle0 {

    /**
     * term := fact { '+' fact }
     */
    @Override
    long evalTerm() {
        long result = evalFact();

        while ("+".equals(token)) {
            nextToken();
            result = result + evalFact();
        }

        return result;
    }

    /**
     * expr := term { '*' term }
     */
    @Override
    long evalExpr() {
        long result = evalTerm();

        while ("*".equals(token)) {
            nextToken();
            result = result * evalTerm();
        }

        return result;
    }

    public static void main(String[] args) {
        Puzzle2 p = new Puzzle2();
        p.process(args[0]);
    }
}
