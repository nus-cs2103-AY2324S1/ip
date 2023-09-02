package duke.main;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;

/**
 * A class for formatting between LocalDateTime object and String based on
 * user input
 */
public class DateFormatter {

    /**
     * Returns LocalDateTime object from String
     * @param date The String containing date from user input
     * @return LocalDateTime
     * @throws DateTimeException if the format of String is wrong
     */
    public LocalDateTime stringToDate(String date) throws DateTimeException {
        String dateFormat = "yyyy-MM-dd HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        return dateTime;
    }

    /**
     * Returns dateTime in a certain format
     * @param dateTime The LocalDateTime containing dateTime from user input
     * @return dateTime in String format
     */
    public String dateToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String result = dateTime.format(formatter);
        return result;
    }

    /**
     * Returns dateTime in a certain format to save in a file
     * @param dateTime The LocalDateTime containing dateTime from user input
     * @return dateTime in String format
     */
    public String saveDateToFile(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String result = dateTime.format(formatter);
        return result;
    }

    /**
     * Returns only date in a certain format from LocalDateTime object
     * @param dateTime The LocalDateTime containing dateTime from user input
     * @return only date in String format
     */
    public String dateTimeToDate(LocalDateTime dateTime) {
        LocalDate date = dateTime.toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String result = date.format(formatter);
        return result;
    }

    /**
     * Returns only time in a certain format from LocalDateTime object
     * @param dateTime The LocalDateTime containing dateTime from user input
     * @return only time in String format
     */
    public String dateTimeToTime(LocalDateTime dateTime) {
        LocalTime time = dateTime.toLocalTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String result = time.format(formatter);
        return result;
    }

}
