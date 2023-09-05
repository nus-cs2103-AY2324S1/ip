package helpers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * This helper class detects the input to be either a date, a time, or both.
 * It then correctly formats the string to a LocalDateTime object.
 */
public class DateTimeDetection {

    /**
     * Returns a LocalDateTime object based in the String input.
     * Inputs without a date are set to the date the task was created.
     * Inputs without a time are set to 23:59HRS on the day the task was created.
     *
     * @param input The user input for date/time.
     * @return A LocalDateTime object.
     */
    public static LocalDateTime detectDateTime(String input) {

        if (isDateTime(input)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
            return dateTime;
        } else if (isDate(input)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(input, formatter);
            return date.atTime(LocalTime.of(23, 59));
        } else if (isTime(input)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime time = LocalTime.parse(input, formatter);
            return LocalDateTime.of(LocalDate.now(), time);
        } else {
            System.out.println("Invalid format.");
            return null;
        }
    }

    /**
     * Determines if the string contains both date and time.
     *
     * @param input
     * @return true if String contains both date and time, false otherwise.
     */
    public static boolean isDateTime(String input) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            LocalDate.parse(input, dateFormatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Determines if the string contains only date.
     *
     * @param input
     * @return true if String only contains date, false otherwise.
     */
    public static boolean isDate(String input) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(input, dateFormatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Determines if the string contains only time.
     *
     * @param input
     * @return true if String only contains time, false otherwise.
     */
    public static boolean isTime(String input) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            LocalTime.parse(input, timeFormatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns an alternative representation of the LocalDateTime object, in the form of
     * dd MM yyyy HH:mm
     * E.g. 20 Oct 2001 18:23
     *
     * @param dateTime
     * @return String representation of LocalDateTime object.
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return dateTime.format(outputFormatter);
    }
}
