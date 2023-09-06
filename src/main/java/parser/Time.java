package parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.InvalidDateException;
import exception.InvalidTimeException;

/**
 * Class for the parsing of a string of date/time data
 */
public class Time {

    /**
     * Takes in a string containing the date and attempts to find a format that the date is written in
     * @param date string of the date data
     * @return DateTimeFormatter containing the corresponding date format
     * @throws InvalidDateException if no appropriate date format is found
     */
    private static DateTimeFormatter findDateFormat(String date) throws InvalidDateException {

        DateTimeFormatter[] formats = new DateTimeFormatter[]{
                DateTimeFormatter.ofPattern("MMM-d-yyyy"),
                DateTimeFormatter.ofPattern("MMM-dd-yyyy"),
                DateTimeFormatter.ofPattern("d-MMM-yyyy"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy"),
                DateTimeFormatter.ofPattern("d-MM-yyyy"),
                DateTimeFormatter.ofPattern("MM-dd-yyyy"),
                DateTimeFormatter.ofPattern("MM-d-yyyy"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("yyyy-MM-d"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("MMM-d-yyyy HHmm"),
                DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm"),
                DateTimeFormatter.ofPattern("d-MMM-yyyy HHmm"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"),
                DateTimeFormatter.ofPattern("d-MM-yyyy HHmm"),
                DateTimeFormatter.ofPattern("MM-dd-yyyy HHmm"),
                DateTimeFormatter.ofPattern("MM-d-yyyy HHmm"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
                DateTimeFormatter.ofPattern("yyyy-MM-d HHmm"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
                DateTimeFormatter.ofPattern("HHmm MMM-d-yyyy"),
                DateTimeFormatter.ofPattern("HHmm MMM-dd-yyyy"),
                DateTimeFormatter.ofPattern("HHmm d-MMM-yyyy"),
                DateTimeFormatter.ofPattern("HHmm dd-MM-yyyy"),
                DateTimeFormatter.ofPattern("HHmm d-MM-yyyy"),
                DateTimeFormatter.ofPattern("HHmm MM-dd-yyyy"),
                DateTimeFormatter.ofPattern("HHmm MM-d-yyyy"),
                DateTimeFormatter.ofPattern("HHmm yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("HHmm yyyy-MM-d"),
                DateTimeFormatter.ofPattern("HHmm yyyy-MM-dd"),

                DateTimeFormatter.ofPattern("MMM d yyyy"),
                DateTimeFormatter.ofPattern("MMM dd yyyy"),
                DateTimeFormatter.ofPattern("d MMM yyyy"),
                DateTimeFormatter.ofPattern("dd MM yyyy"),
                DateTimeFormatter.ofPattern("d MM yyyy"),
                DateTimeFormatter.ofPattern("MM dd yyyy"),
                DateTimeFormatter.ofPattern("MM d yyyy"),
                DateTimeFormatter.ofPattern("yyyy MM dd"),
                DateTimeFormatter.ofPattern("yyyy MM d"),
                DateTimeFormatter.ofPattern("yyyy MM dd"),
                DateTimeFormatter.ofPattern("MMM d yyyy HHmm"),
                DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"),
                DateTimeFormatter.ofPattern("d MMM yyyy HHmm"),
                DateTimeFormatter.ofPattern("dd MM yyyy HHmm"),
                DateTimeFormatter.ofPattern("d MM yyyy HHmm"),
                DateTimeFormatter.ofPattern("MM dd yyyy HHmm"),
                DateTimeFormatter.ofPattern("MM d yyyy HHmm"),
                DateTimeFormatter.ofPattern("yyyy MM dd HHmm"),
                DateTimeFormatter.ofPattern("yyyy MM d HHmm"),
                DateTimeFormatter.ofPattern("yyyy MM dd HHmm"),
                DateTimeFormatter.ofPattern("HHmm MMM d yyyy"),
                DateTimeFormatter.ofPattern("HHmm MMM dd yyyy"),
                DateTimeFormatter.ofPattern("HHmm d MMM yyyy"),
                DateTimeFormatter.ofPattern("HHmm dd MM yyyy"),
                DateTimeFormatter.ofPattern("HHmm d MM yyyy"),
                DateTimeFormatter.ofPattern("HHmm MM dd yyyy"),
                DateTimeFormatter.ofPattern("HHmm MM d yyyy"),
                DateTimeFormatter.ofPattern("HHmm yyyy MM dd"),
                DateTimeFormatter.ofPattern("HHmm yyyy MM d"),
                DateTimeFormatter.ofPattern("HHmm yyyy MM dd"),

                DateTimeFormatter.ofPattern("MMM/d/yyyy"),
                DateTimeFormatter.ofPattern("MMM/dd/yyyy"),
                DateTimeFormatter.ofPattern("d/MMM/yyyy"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("d/MM/yyyy"),
                DateTimeFormatter.ofPattern("MM/dd/yyyy"),
                DateTimeFormatter.ofPattern("MM/d/yyyy"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd"),
                DateTimeFormatter.ofPattern("yyyy/MM/d"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd"),
                DateTimeFormatter.ofPattern("MMM/d/yyyy HHmm"),
                DateTimeFormatter.ofPattern("MMM/dd/yyyy HHmm"),
                DateTimeFormatter.ofPattern("d/MMM/yyyy HHmm"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"),
                DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"),
                DateTimeFormatter.ofPattern("MM/dd/yyyy HHmm"),
                DateTimeFormatter.ofPattern("MM/d/yyyy HHmm"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"),
                DateTimeFormatter.ofPattern("yyyy/MM/d HHmm"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"),
                DateTimeFormatter.ofPattern("HHmm MMM/d yyyy"),
                DateTimeFormatter.ofPattern("HHmm MMM/dd yyyy"),
                DateTimeFormatter.ofPattern("HHmm d/MMM/yyyy"),
                DateTimeFormatter.ofPattern("HHmm dd/MM/yyyy"),
                DateTimeFormatter.ofPattern("HHmm d/MM/yyyy"),
                DateTimeFormatter.ofPattern("HHmm MM/dd/yyyy"),
                DateTimeFormatter.ofPattern("HHmm MM/d/yyyy"),
                DateTimeFormatter.ofPattern("HHmm yyyy/MM/dd"),
                DateTimeFormatter.ofPattern("HHmm yyyy/MM/d"),
                DateTimeFormatter.ofPattern("HHmm yyyy/MM/dd"),
        };

        for (DateTimeFormatter format: formats) {
            if (hasSameDateFormat(date, format)) {
                return format;
            }
        }

        throw new InvalidDateException();
    }

    /**
     * Checks if the date string has the same date format in the formatter
     * @param date string of the date data
     * @param formatter date format to be checked with
     * @return boolean indicating if its the same format or not
     */
    private static boolean hasSameDateFormat(String date, DateTimeFormatter formatter) {
        try {
            LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Takes in a string containing date, validates if its a valid date and then
     * returns the date in a standardized format
     * @param date string containing the user input date
     * @return date string in a standardised format
     * @throws InvalidDateException if date is an invalid date
     */
    public static String formatDate(String date) throws InvalidDateException {
        DateTimeFormatter stdFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        DateTimeFormatter currentFormat = findDateFormat(date);
        return LocalDate.parse(date, currentFormat).format(stdFormat);
    }

    /**
     * Takes in a string containing time, validates if its a valid time and then
     * returns the date/time in a standardized format
     * @param date string date input by user
     * @param time string time input by user
     * @return date and time string in standardised format
     * @throws InvalidTimeException if input time is not a valid time
     * @throws InvalidDateException if date is not a valid date
     */
    public static String formatTime(String date, String time) throws InvalidTimeException, InvalidDateException {

        DateTimeFormatter stdFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy HHmm");
        DateTimeFormatter currentFormat = findDateFormat(date);

        LocalDate lDate = LocalDate.parse(date, currentFormat);

        int intTime = Integer.parseInt(time);
        int hour = (int) Math.floor(intTime / 100.0);
        int minute = intTime - (hour * 100);

        return lDate.atTime(hour, minute).format(stdFormat);
    }
}
