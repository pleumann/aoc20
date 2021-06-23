package day17;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;

/**
 * Conway Cubes part 2.
 */
public class Puzzle2 {
    
    HashSet<String> bits = new HashSet();

    int minX = Integer.MAX_VALUE;
    int maxX = Integer.MIN_VALUE;

    int minY = Integer.MAX_VALUE;
    int maxY = Integer.MIN_VALUE;

    int minZ = Integer.MAX_VALUE;
    int maxZ = Integer.MIN_VALUE;

    int minW = Integer.MAX_VALUE;
    int maxW = Integer.MIN_VALUE;

    int cycles;

    boolean get(int x, int y, int z, int w) {
        return bits.contains("" + x + "," + y + "," + z + "," + w);
    }

    void put(int x, int y, int z, int w) {
        bits.add("" + x + "," + y + "," + z + "," + w);

        minX = Math.min(minX, x);
        maxX = Math.max(maxX, x);
        minY = Math.min(minY, y);
        maxY = Math.max(maxY, y);
        minZ = Math.min(minZ, z);
        maxZ = Math.max(maxZ, z);
        minW = Math.min(minW, w);
        maxW = Math.max(maxW, w);
    }

    void load(String file) {
        try {
            List<String> list = Files.readAllLines(new File(file).toPath());
            String[] input = list.toArray(new String[list.size()]);            
            
            for (int i = 0; i < input.length; i++) {
                String s = input[i];
                for (int j = 0; j < s.length(); j++) {
                    if (s.charAt(j) == '#') {
                        put(j, i, 0, 0);
                    }
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    int count(int x, int y, int z, int w) {
        int result = 0;

        for (int i = - 1; i < 2; i++) {
            for (int j = - 1; j < 2; j++) {
                for (int k = - 1; k < 2; k++) {
                    for (int l = - 1; l < 2; l++) {
                        if (i != 0 || j != 0 || k != 0 || l != 0) {
                            if (get(x + i, y + j, z + k, w + l)) {
                                result = result + 1;
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

    Puzzle2 next() {
        Puzzle2 result = new Puzzle2();
        
        result.cycles = cycles + 1;
        
        for (int w = minW - 1; w <= maxW + 1; w++) {
            for (int z = minZ - 1; z <= maxZ + 1; z++) {
                for (int y = minY - 1; y <= maxY + 1; y++) {
                    for (int x = minX - 1; x <= maxX + 1; x++) {
                        int c = count(x, y, z, w);
                        if (get(x, y, z, w) && c == 2) {
                            result.put(x, y, z, w);
                        } else if (c == 3) {
                            result.put(x, y, z, w);
                        }
                    }
                }
            }
        }
        
        return result;
    }
    
    void dump() {
        System.out.println();            
        System.out.println("After " + cycles + " cycles");            
        System.out.println();

        for (int w = minW; w <= maxW; w++) {
            for (int z = minZ; z <= maxZ; z++) {
                System.out.println();
                System.out.println("z=" + z + ",w=" + w);            
                for (int y = minY; y <= maxY; y++) {
                    for (int x = minX; x <= maxX; x++) {
                        if (get(x, y, z, w)) {
                            System.out.print("#");
                        } else {
                            System.out.print(".");
                        }
                    }
                    System.out.println();
                }
            }
        }
        
        
        System.out.println();        
        System.out.println("Count=" + bits.size());
        System.out.println();        
    }
    
    public static void main(String[] args) {
        Puzzle2 p = new Puzzle2();
        p.load(args[0]);
        
        for (int i = 1; i < 7; i++) {
            p = p.next();
            p.dump();
        }
    }
    
}
