package day17;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Conway Cubes part 2, threaded variant suitable for benchmarking.
 */
public class Puzzle2Threaded extends Puzzle2 {
    
    int threads;
    
    Puzzle2Threaded(int threads) {
        this.threads = threads;
    }
    
    @Override
    Puzzle2 next() {
        Puzzle2 result = new Puzzle2Threaded(threads);
        
        result.cycles = cycles + 1;

        ThreadPoolExecutor exe = (ThreadPoolExecutor) Executors.newFixedThreadPool(threads);
        
        for (int w = minW - 1; w < maxW + 2; w++) {
            final int ww = w;
            exe.submit(new Runnable() {
                @Override
                public void run() {
                    for (int z = minZ - 1; z < maxZ + 2; z++) {
                        final int zz = z;
                        for (int y = minY - 1; y < maxY + 2; y++) {
                            for (int x = minX - 1; x < maxX + 2; x++) {
                                int c = count(x, y, zz, ww);
                                if (get(x, y, zz, ww) && c == 2) {
                                    result.put(x, y, zz, ww);
                                } else if (c == 3) {
                                    result.put(x, y, zz, ww);
                                }
                            }
                        }
                    }
                }
            });
        }

        System.out.println("ThreadPoolExecutor has " + exe.getTaskCount() + " jobs to do.");
        
        exe.shutdown();
        try {
            exe.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: ... <input> <cycles> <threads>");
            System.exit(1);
        }

        int cycles = Integer.parseInt(args[1]);
        
        Puzzle2 p = new Puzzle2Threaded(Integer.parseInt(args[2]));
        p.load(args[0]);
        
        for (int i = 0; i < cycles; i++) {
            p = p.next();
            //p.dump();
            
            System.out.println("Count=" + p.bits.size() + " after " + p.cycles + " cycles");
            System.out.println();
        }
    }
    
}
