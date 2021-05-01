package day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Seating System part 1. Basically a Game of Life variant.
 */
public class Puzzle1 {
    
    char[][] seats, next; // Use two buffers
    
    int rows, columns, occupied, threshold;

    public Puzzle1() {
        threshold = 4;
    }
    
    void loadInput(String file) {
        ArrayList<char[]> list = new ArrayList();
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String s = reader.readLine();
            while (s != null) {
                list.add(s.toCharArray());
                s = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        rows = list.size();
        columns = list.get(0).length;
        
        seats = list.toArray(new char[0][]);
        next = new char[rows][];
        for (int i = 0; i < rows; i++) {
            next[i] = new char[columns];
        }
    }
        
    char get(int x, int y) {
        if (x < 0 || x >= rows) {
            return ' ';
        }
        
        if (y < 0 || y >= columns) {
            return ' ';
        }
        
        return seats[x][y];
    }

    void set(int x, int y, char c) {
        next[x][y] = c;
    }
    
    int count(int x, int y) {
        int result = 0;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i != 0 || j != 0) {
                    if (get(x + i, y + j) == '#') {
                        result++;
                    }
                }
            }
        }
        
        return result;
    }
    
    boolean think() {
        boolean changed = false;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                char c = get(i ,j);
                int n = count(i, j);

                if (c == 'L') {
                    if (n == 0) {
                        c = '#';
                        occupied = occupied + 1;
                        changed = true;
                    }
                } else if (c == '#') {
                    if (n >= threshold) {
                        c = 'L';
                        occupied = occupied - 1;
                        changed = true;
                    }
                }

                set(i, j, c);
            }
        }
        
        char[][] temp = seats;
        seats = next;
        next = temp;
        
        return changed;
    }
    
    void print() {
        for (char[] seat : seats) {
            System.out.println(new String(seat));
        }
        System.out.println(occupied);
        System.out.println();
    }

    public static void main(String[] args) {
        Puzzle1 p = new Puzzle1();
        p.loadInput(args[0]);
        
        int i = 0;
        while (p.think()) { 
            p.print();
            i++; 
        }
    }
    
}
