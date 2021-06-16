package day16;

/**
 * Ticket Translation part 2.
 */
public class Puzzle2 extends Puzzle1 {

    /**
     * Resolves the next field from the given array of bit masks. Adjusts index
     * of field just found and removes the field from all bit masks. -1 if no
     * field could be resolved.
     */
    int resolve(int[] bits) {
        for (int i = 0; i < bits.length; i++) {
            int b = bits[i];
            if (Integer.bitCount(b) == 1) {
                int c = Integer.numberOfTrailingZeros(b);
                fields.get(c).index = i;                
                for (int j = 0; j < bits.length; j++) {
                    bits[j] = bits[j] & ~b;
                }
                return c;
            }
        }
        
        return -1;
    }
    
    @Override
    long process(String file) {
        super.process(file);
            
        long product = 1;
        int i = resolve(columnFields);
        while (i != -1) {
            Field f = fields.get(i); 

            System.out.println("Field " + i + " is " + f.name);

            if (f.name.startsWith("departure")) {
                product = product * myTicket[f.index];
            }

            i = resolve(columnFields);
        }

        return product;
    }
    
    public static void main(String[] args) {
        Puzzle2 p = new Puzzle2();
        System.out.println("Result=" + p.process(args[0]));
    }
}
