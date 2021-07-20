package day19;

/**
 * Monster Messages part 2.
 */
public class Puzzle2 extends Puzzle1 {

    @Override
    String transform() {
        
        // Replacement rule "8: 42 | 42 8"
        //
        // This is recursive, but luckily equivalent to the following
        // regular expression:

        rules.put("8", " 42+ ");

        // Replacement rule "11: 42 31 | 42 11 31"
        //
        // This is recursive, too, and cannot be expressed using ordinary
        // regular expressions. Actually it's exactly the counting/balancing
        // example typically used in CS lectures to draw the boundary
        // between regular and context-free languages.
        //
        // Some regex implementations actually do support that, doubtless
        // by applying dark magic. Java does not, so we need a bit of
        // Kobayashi Maru here - aka we're cheating. How many repetitions can
        // one actually need? Turns out 6 are anough. ;)

        rules.put("11", " 42 31 |  42 42 31 31 |  42 42 42 31 31 31 | 42 42 42 42 31 31 31 31 |  42 42 42 42 42 31 31 31 31 31 |  42 42 42 42 42 42 31 31 31 31 31 31 ");

        return super.transform();
    }
    
    public static void main(String[] args) {
        Puzzle2 p = new Puzzle2();
        p.process(args[0]);
    }
    
}
