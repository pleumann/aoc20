package util;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author joerg
 */
public class Helper {

    public static String[] loadStrings(String file) {
        ArrayList<String> list = new ArrayList();
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            
            String s = reader.readLine();
            while (s != null) {
                list.add(s);
                s = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        return list.toArray(new String[list.size()]);
    }

    public static int[] loadIntArray(String file) {
        String[] strings = loadStrings(file);
        int[] integers = new int[strings.length];
        
        for (int i = 0; i < strings.length; i++) {
            integers[i] = Integer.parseInt(strings[i]);
        }
        
        return integers;
    }
    
}
