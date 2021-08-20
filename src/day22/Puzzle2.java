package day22;

import java.util.HashSet;

/**
 * Crab Combat part 2. Adds history and recursive sub-games.
 */
public class Puzzle2 extends Puzzle1 {

    int game;

    HashSet<String> history = new HashSet();

    Puzzle2(int game) {
        super();

        this.game = game;
    }

    @Override
    int playGame() {
        while (true) {
            round++;

            String p1 = toString(1);
            String p2 = toString(2);

            System.out.println("--- Round " + round + " ---");
            System.out.println("Player 1: " + p1);
            System.out.println("Player 2: " + p2);

            if (history.contains(p1 + p2)) {
                System.out.println("Repeated config. Player 1 wins game " + game + ".");
                return 1;
            }

            history.add(p1 + p2);

            if (player1.isEmpty()) {
                System.out.println("Player 1 out of cards. Player 2 wins game " + game + ".");
                return 2;
            }

            if (player2.isEmpty()) {
                System.out.println("Player 2 out of cards. Player 1 wins game " + game + ".");
                return 1;
            }

            int i1 = player1.remove(0);
            int i2 = player2.remove(0);

            if (player1.size() >= i1 && player2.size() >= i2) {
                Puzzle2 sub = new Puzzle2(game + 1);

                sub.player1.addAll(player1.subList(0, i1));
                sub.player2.addAll(player2.subList(0, i2));

                System.out.println("Sub-game " + sub.game + " started.");

                if (sub.playGame() == 1) {
                    player1.add(i1);
                    player1.add(i2);
                } else {
                    player2.add(i2);
                    player2.add(i1);
                }

                System.out.println("Back to game " + game + ".");
            } else {
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
    }

    public static void main(String[] args) {
        Puzzle2 p = new Puzzle2(1);
        p.loadInput(args[0]);
        p.playGame();
    }
}
