package tasks;

import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeUtility {
    /**
     * Converts a date and time string into a LocalDateTime object.
     *
     * @param dateTimeString The date and time string to be parsed.
     * @return The LocalDateTime object representing the date and time.
     */
    public static LocalDateTime stringToDate(String dateTimeString) throws DukeException {
        DateTimeFormatter formatter;
        dateTimeString = dateTimeString.trim();
        try {
            if (dateTimeString.contains("T")) {
                formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            } else {
                formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            }
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException timeParseError) {
            throw new DukeException(" Hmmm seems like the date/time format is incorrect; "
                    + "take a look at the valid format: d/M/yyyy HHmm");
        }
    }

    /**
     * Converts a LocalDateTime object into a formatted string.
     *
     * @param dateTime The LocalDateTime object to be formatted.
     * @return The formatted date and time string.
     */
    public static String dateToString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }
}
