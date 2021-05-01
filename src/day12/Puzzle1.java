package day12;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Rain Risk part 1. Direct navigation.
 */
public class Puzzle1 {

    final String LEFT = "NWSE";
    
    final String RIGHT = "ENWS";
    
    String[] input;

    int x,y;

    char facing = 'E';
    
    void loadInput(String file) {
        try {
            List<String> list = Files.readAllLines(new File(file).toPath());
            input = list.toArray(new String[list.size()]);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
        
    public void move(char dir, int distance) {
        switch (dir) {
            case 'N': y = y + distance; break;
            case 'S': y = y - distance; break;
            case 'W': x = x + distance; break;
            case 'E': x = x - distance; break;
        }
    }
    
    public void left(int angle) {
        while (angle >= 90) {
            facing = LEFT.charAt(RIGHT.indexOf(facing));
            angle = angle - 90;
        }
    }

    public void right(int angle) {
        while (angle >= 90) {
            facing = RIGHT.charAt(LEFT.indexOf(facing));
            angle = angle - 90;
        }
    }
    
    void navigate() {
        for (String s: input) {
            System.out.println(s);
            
            char c = s.charAt(0);
            int value = Integer.parseInt(s.substring(1));
            
            if (c == 'F') {
                move(facing, value);
            } else if (c == 'L') {
                left(value);
            } else if (c == 'R') {
                right(value);
            } else {
                move(c, value);
            }
            
            System.out.println("x=" + x + " y=" + y + " facing=" + facing);
        }
    }
    
    public static void main(String[] args) {
        Puzzle1 p = new Puzzle1();
        p.loadInput(args[0]);
        p.navigate();
        
        System.out.println("Manhattan distance is " + (Math.abs(p.x) + Math.abs(p.y)));
    }
    
}
