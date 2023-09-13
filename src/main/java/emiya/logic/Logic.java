package emiya.logic;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;

import java.time.YearMonth;

import emiya.Keyword;

/**
 * A class that contains methods to check whether given inputs are valid as well as what type of input it is
 */
public class Logic {

    // Used to check whether a given string contains purely numeric values.
    public static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    public static boolean isValidYear(int year) {
        return (MIN_VALUE < year && year < MAX_VALUE);
    }

    public static boolean isValidMonth(int month) {
        return (month > 0 && month < 13);
    }

    public static boolean isValidDay(int day, int month, int year) {
        YearMonth yearMonth = YearMonth.of(year, month);
        return (day > 0 && day <= yearMonth.lengthOfMonth());
    }

    public static boolean isValidHour(int hour) {
        return (hour >= 0 && hour <= 23);
    }

    public static boolean isValidMinute(int min) {
        return (min >= 0 && min <= 59);
    }

    public static boolean isInvalidDateTime(int year, int month, int day, int hour, int min) {
        return (!isValidYear(year) || !isValidMonth(month) || !isValidDay(day, month, year)
                || !isValidHour(hour) || !isValidMinute(min));
    }

    public static boolean isWrongTimeFormat(String timePart) {
        String hourStr = timePart.substring(0, 2);
        String minStr = timePart.substring(2, 4);

        boolean isWrongInputLengthForTime = timePart.length() != 4;
        boolean isNotNumericInputTime = !Logic.isNumeric(hourStr)
                || !Logic.isNumeric(minStr);
        return isWrongInputLengthForTime || isNotNumericInputTime;
    }

    public static boolean isWrongDateFormat(String[] partsOfDate) {
        boolean isWrongInputFormatForDate = partsOfDate.length < 3;
        boolean isNotNumericInputDate = !Logic.isNumeric(partsOfDate[0]) || !Logic.isNumeric(partsOfDate[1])
                || !Logic.isNumeric(partsOfDate[2]);
        return isWrongInputFormatForDate || isNotNumericInputDate;

    }

    /**
     * A method that helps to check whether a given String input is a part of the reserved keyword enum.
     * @param test The input that is to be checked against the keyword enum.
     * @return A boolean that indicates whether the given input is part of the keyword enum.
     */
    public static boolean enumContainsKeyword(String test) {
        for (Keyword k : Keyword.values()) {
            if (k.name().equals(test)) {
                return true;
            }
        }
        return false;
    }
}
