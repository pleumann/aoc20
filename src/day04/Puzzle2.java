package day04;

/**
 * Passport Processing 2.
 */
public class Puzzle2 extends Puzzle1 {

    // Note: loadInput() method inherited from Puzzle1.
    
    String getField(String s, String key) {
        int i = s.indexOf(key + ':');
        if (i == -1) {
            return "";
        }

        int j = s.indexOf(' ', i + 2);
        if (j == -1) {
            j = s.length();
        }

        return s.substring(i + key.length() + 1, j);
    }

    boolean checkYear(String s, int min, int max) {
        if (!s.matches("[0-9]{4}")) {
            return false;
        }

        int i = Integer.parseInt(s);
        if (i < min || i > max) {
            return false;
        }

        return true;
    }

    boolean checkHeight(String s) {
        if (s.matches("[0-9]+cm")) {
            int i = Integer.parseInt(s.substring(0, s.length() - 2));
            return 150 <= i && i <= 193;
        } else if (s.matches("[0-9]+in")) {
            int i = Integer.parseInt(s.substring(0, s.length() - 2));
            return 59 <= i && i <= 76;
        }

        return false;
    }

    boolean checkHair(String s) {
        return s.matches("#[0-9a-f]{6}");
    }

    boolean checkEye(String s) {
        return s.matches("amb|blu|brn|gry|grn|hzl|oth");
    }

    boolean checkPassId(String s) {
        return s.matches("[0-9]{9}");
    }

    @Override
    boolean isValid(String s) {
        if (!checkYear(getField(s, "byr"), 1920, 2002)) return false;
        if (!checkYear(getField(s, "iyr"), 2010, 2020)) return false;
        if (!checkYear(getField(s, "eyr"), 2020, 2030)) return false;
        if (!checkHeight(getField(s, "hgt"))) return false;
        if (!checkHair(getField(s, "hcl"))) return false;
        if (!checkEye(getField(s, "ecl"))) return false;
        if (!checkPassId(getField(s, "pid"))) return false;

        return true;
    }

    public static void main(String[] args) {
        Puzzle2 p = new Puzzle2();
        p.loadInput(args[0]);
    }
    
}
