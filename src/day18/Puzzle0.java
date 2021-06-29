package day18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Operation Order base class. This one does proper math. It demonstrates how to
 * implement an expression evaluator using a simple recursive descent parser.
 * 
 * BNF grammar is as follows:
 * 
 * expr := term { '+' term }
 * term := fact { '*' fact }
 * fact := '(' expr ')' | num
 * 
 * Scanner returns either a whole number or just the next character. Input is
 * terminated by a sentinel '$'.
 */
public class Puzzle0 {

    /**
     * The whole input including the sentinel character.
     */
    String input;

    /**
     * The position of the next character to process.
     */
    int position;

    /**
     * The last token found.
     */
    String token;

    /**
     * Finds the next token, which is either a whole number or just the next
     * character found. Historically, this is called the scanner, newer software
     * often calls it the tokenizer.
     */
    void nextToken() {
        token = "";

        char c = input.charAt(position);
        while (Character.isSpaceChar(c)) {
            c = input.charAt(++position);
        }

        if (Character.isDigit(c)) {
            while (Character.isDigit(c)) {
                token = token + c;
                c = input.charAt(++position);
            }
        } else {
            token = token + c;
            position++;
        }
    }

    /**
     * fact := '(' expr ')' | num
     */
    long evalFact() {
        long result;

        if ("(".equals(token)) {
            nextToken();
            result = evalExpr();
            if (!")".equals(token)) {
                throw new RuntimeException("Parse error, ')' expected.");
            }
            nextToken();
        } else {
            result = Long.parseLong(token);
            nextToken();
        }

        return result;
    }

    /**
     * term := fact { '*' fact }
     */
    long evalTerm() {
        long result = evalFact();

        while ("*".equals(token)) {
            nextToken();
            result = result * evalFact();
        }

        return result;
    }

    /**
     * expr := term { '+' term }
     */
    long evalExpr() {
        long result = evalTerm();

        while ("+".equals(token)) {
            nextToken();
            result = result + evalTerm();
        }

        return result;
    }

    /**
     * Evaluator entry point. Appends the sentinel '$' character and kicks off
     * the parsing.
     */
    long eval(String s) {
        input = s + '$';
        position = 0;
        nextToken();

        long result = evalExpr();

        if (!"$".equals(token)) {
            throw new RuntimeException("Parse error, EOL expected.");
        }

        return result;
    }

    /**
     * Processes the whole input file, summing up all intermediate results.
     */
    void process(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            long total = 0;
            
            String s = reader.readLine();
            while (s != null) {
                long i = eval(s);
                System.out.println(s + " = " + i);
                total = total + i;
                s = reader.readLine();
            }
            
            System.out.println("Grand total=" + total);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        Puzzle0 p = new Puzzle0();
        p.process(args[0]);
    }
}
