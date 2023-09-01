package pippi.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateFormatter {
    public static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /**
     * Returns a LocalDate representation of the input String
     * @param date String of date in yyyy-MM-dd format
     * @return LocalDate
     */
    public static LocalDate convertToLocalDate(String date) {
        return LocalDate.parse(date, format);
    }

    /**
     * Returns a String representation of the input LocalDate
     * @param date LocalDate
     * @return String in MMM dd yyyy format
     */
    public static String convertDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Returns true if date string is in valid format of yyyy-MM-dd
     * @param date String of input date
     * @return boolean value of whether string is in correct format
     */
    public static boolean isValidFormat(String date) {
        try {
            System.out.println("here");
            LocalDate.parse(date, format);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
