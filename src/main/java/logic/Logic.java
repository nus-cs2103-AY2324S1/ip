package logic;

public class Logic {

    // Used to check whether a given string contains purely numeric values.
    public static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
}
