package emiya.logic;

import emiya.Keywords;

/**
 * A class that contains methods to check whether given inputs are valid as well as what type of input it is
 */
public class Logic {

    // Used to check whether a given string contains purely numeric values.
    public static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    public static boolean isValidYear(int year) {
        return (year > 0 && year < 9999);
    }

    public static boolean isValidMonth(int month) {
        return (month > 0 && month < 13);
    }

    public static boolean isValidDay(int day) {
        return (day > 0 && day < 32);
    }

    public static boolean isValidHour(int hour) {
        return (hour >= 0 && hour <= 23);
    }

    public static boolean isValidMinute(int min) {
        return (min >= 0 && min <= 59);
    }

    /**
     * A method that helps to check whether a given String input is a part of the reserved keyword enum.
     * @param test The input that is to be checked against the keyword enum.
     * @return A boolean that indicates whether the given input is part of the keyword enum.
     */
    public static boolean enumContainsKeyword(String test) {
        for (Keywords k : Keywords.values()) {
            if (k.name().equals(test)) {
                return true;
            }
        }
        return false;
    }
}
