package day12;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Rain Risk part 2. Indirect navigation via waypoint.
 */
public class Puzzle2 {

    String[] input;

    int x,y;
    
    int wx = 10;
    int wy = 1;

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
            case 'N': wy = wy + distance; break;
            case 'S': wy = wy - distance; break;
            case 'E': wx = wx + distance; break;
            case 'W': wx = wx - distance; break;
        }
    }
    
    public void left(int angle) {
        while (angle >= 90) {
            int tmp = wx;
            wx = -wy;
            wy = tmp;
            angle = angle - 90;
        }
    }

    public void right(int angle) {
        while (angle >= 90) {
            int tmp = wx;
            wx = wy;
            wy = -tmp;
            angle = angle - 90;
        }
    }
    
    public void forward(int count) {
        for (int i = 0; i < count; i++) {
            x = x + wx;
            y = y + wy;
        }
    }
    
    void navigate() {
        for (String s: input) {
            System.out.println(s);
            
            char c = s.charAt(0);
            int value = Integer.parseInt(s.substring(1));
            
            if (c == 'F') {
                forward(value);
            } else if (c == 'L') {
                left(value);
            } else if (c == 'R') {
                right(value);
            } else {
                move(c, value);
            }
            
            System.out.println("x=" + x + " y=" + y + " wx=" + wx + " wy=" + wy);
        }
    }
    
    public static void main(String[] args) {
        Puzzle2 p = new Puzzle2();
        p.loadInput(args[0]);
        p.navigate();
        
        System.out.println("Manhattan distance is " + (Math.abs(p.x) + Math.abs(p.y)));
    }
    
}
