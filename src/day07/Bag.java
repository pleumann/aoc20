package day07;

import java.util.ArrayList;

/**
 * Represents a bag including its name, its children and the cardinalities of
 * the children.
 */
public class Bag {
   
    String name;

    ArrayList<Integer> numbers = new ArrayList();
    
    ArrayList<Bag> children = new ArrayList();

    int total = -1;
        
    public Bag(String s) {
        name = s;
    }

    void add(int count, Bag bag) {
        numbers.add(count);
        children.add(bag);
    }
    
    boolean contains(Bag bag) {
        for (Bag child: children) {
            if (child == bag || child.contains(bag)) return true;
        }
        
        return false;
    }
    
    int size() {
        if (total != -1) {
            return total;
        }

        total = 1;

        for (int i = 0; i < children.size(); i++) {
            total = total + numbers.get(i) * children.get(i).size();
        }

        return total;
    }
        
}
