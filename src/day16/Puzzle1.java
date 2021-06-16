package day16;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Ticket Translation part 1.
 */
public class Puzzle1 {

    /**
     * Helper class representing field definitions.
     */
    class Field {

        String name;

        int min1, max1, min2, max2;
        
        int index;

        Field(String s) {
            String[] a = s.split(": |-| or ");

            name = a[0];

            min1 = Integer.parseInt(a[1]);
            max1 = Integer.parseInt(a[2]);
            min2 = Integer.parseInt(a[3]);
            max2 = Integer.parseInt(a[4]);
        }
        
        boolean isValid(int value) {
            return (min1 <= value && value <= max1) || (min2 <= value && value <= max2);
        }

    }
    
    /**
     * List of field definitions.
     */
    ArrayList<Field> fields = new ArrayList();
    
    /**
     * Values for my ticket.
     */
    int[] myTicket;

    /**
     * Bit masks for "which column can be which field(s)".
     */
    int[] columnFields;

    /**
     * Parses a CSV string into an array of int values.
     */
    int[] parse(String s) {
        String[] strings = s.split(",");
        int[] values = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            values[i] = Integer.parseInt(strings[i]);
        }
        return values;
    }
    
    /**
     * Returns the valid fields for value i as a bit mask. 0 means the value is
     * valid for none of the fields.
     */
    int getValidFields(int i) {
        int result = 0;
        for (int j = 0; j < fields.size(); j++) {
            if (fields.get(j).isValid(i)) {
                result = result | (1<<j);
            }
        }
       return result; 
    }
    
    long process(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String s = reader.readLine();
            while (!"".equals(s)) {
                fields.add(new Field(s));
                s = reader.readLine();
            }

            reader.readLine(); // Skip heading "Your ticket"
            
            myTicket = parse(reader.readLine());

            reader.readLine(); // Skip empty line
            reader.readLine(); // Skip heading "Nearby tickets"

            int error = 0;

            columnFields = new int[fields.size()];
            for (int i = 0; i < fields.size(); i++) {
                columnFields[i] = 0xffffffff;
            }
            
            s = reader.readLine();
            while (s != null) {
                int[] values = parse(s);
                int[] validOnes = new int[values.length];
                
                boolean valid = true;
                for (int i = 0; i < values.length; i++) {
                   validOnes[i] = getValidFields(values[i]);
                   if (validOnes[i] == 0) {
                        error = error + values[i];
                        valid = false;
                    }
                }

                if (valid) {
                    for (int i = 0; i < values.length; i++) {
                        columnFields[i] = columnFields[i] & validOnes[i];
                    }
                    System.out.print('*');
                } else {
                    System.out.print('-');
                }

                s = reader.readLine();
            }

            System.out.println();
            
            return error;

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        return 0;
    }
    
    public static void main(String[] args) {
        Puzzle1 p = new Puzzle1();
        System.out.println("Result=" + p.process(args[0]));
    }
}
