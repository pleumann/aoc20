package day22;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Crab Combat part 1.
 */
public class Puzzle1 {

    int round;

    ArrayList<Integer> player1 = new ArrayList();

    ArrayList<Integer> player2 = new ArrayList();

    void loadInput(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String s;

            reader.readLine();              // Player 1
            s = reader.readLine();          // Cards...
            while (!"".equals(s)) {
                player1.add(Integer.parseInt(s));
                s = reader.readLine();
            }

            reader.readLine();              // Player 2
            s = reader.readLine();          // Cards...
            while (s != null) {
                player2.add(Integer.parseInt(s));
                s = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    String toString(int player) {
        ArrayList<Integer> cards = player == 1 ? player1 : player2;
        
        String result = "";
        
        int score = 0;
        int factor = cards.size();
        for (Integer i : cards) {
            result = result + " " + i;
            score = score + i * factor--;
        }
        result = result + " [Score: " + score + "]";

        return result;
    }
    
    int playGame() {
        while (true) {
            round++;
            
            String p1 = toString(1);
            String p2 = toString(2);

            System.out.println("--- Round " + round + " ---");
            System.out.println("Player 1: " + p1);
            System.out.println("Player 2: " + p2);

            if (player1.isEmpty()) {
                System.out.println("Player 1 out of cards. Player 2 wins game.");
                return 2;
            }

            if (player2.isEmpty()) {
                System.out.println("Player 2 out of cards. Player 1 wins game.");
                return 1;
            }

            Integer i1 = player1.get(0);
            player1.remove(0);

            Integer i2 = player2.get(0);
            player2.remove(0);

            if (i1 > i2) {
                System.out.println("Player 1 wins round.");
                player1.add(i1);
                player1.add(i2);
            } else {
                System.out.println("Player 2 wins round.");
                player2.add(i2);
                player2.add(i1);
            }
        }
    }

    public static void main(String[] args) {
        Puzzle1 p = new Puzzle1();
        p.loadInput(args[0]);
        p.playGame();
    }
}
