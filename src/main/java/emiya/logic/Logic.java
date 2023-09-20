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

    /**
     * Returns whether a given month, day and year input is valid.
     * @param day The integer representing the day of the month.
     * @param month The integer representing the month of the year.
     * @param year The year to check for.
     * @return Boolean representing whether a given input is a valid day.
     */
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

    /**
     * Returns whether a given date and time is invalid or not
     * @param year The year to check for.
     * @param month The integer representing the month of the year.
     * @param day The integer representing the day of the month.
     * @param hour The hour to check for, in 24-hour format.
     * @param min The minutes to check for.
     * @return Boolean representing whether a given input is invalid or not.
     */

    public static boolean isInvalidDateTime(int year, int month, int day, int hour, int min) {
        return (!isValidYear(year) || !isValidMonth(month) || !isValidDay(day, month, year)
                || !isValidHour(hour) || !isValidMinute(min));
    }

    /**
     * Returns whether the input is in the wrong format required to be used by the application.
     * @param timePart The String containing the user's input for the time of the task.
     * @return Boolean representing whether the given string is in the wrong format or not.
     */
    public static boolean isWrongTimeFormat(String timePart) {
        String hourStr = timePart.substring(0, 2);
        String minStr = timePart.substring(2, 4);

        boolean isWrongInputLengthForTime = timePart.length() != 4;
        boolean isNotNumericInputTime = !Logic.isNumeric(hourStr)
                || !Logic.isNumeric(minStr);
        return isWrongInputLengthForTime || isNotNumericInputTime;
    }

    /**
     * Returns whether the input is in the wrong format required to be used by the application.
     * @param partsOfDate The String array containing the user's input for the date of the task.
     * @return Boolean representing whether the given string is in the wrong format or not.
     */
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
