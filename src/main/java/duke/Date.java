package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * A date object used to store all things related to the date for a task
 */
public class Date {

    protected LocalDate date;
    protected LocalTime time;

    /**
     * The constructor for the date object
     * @param dateString the date of the task
     * @param timeString the time of the task
     */
    public Date(String dateString, String timeString) {
        String str = "2016-03-04 11:30";

        // Checking if the date or time String is null since they are optional
        date = dateString == null ? null : LocalDate.parse(dateString);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        time = timeString == null ? null : LocalTime.parse(timeString, formatter);
    }

    @Override
    public String toString() {
        String dateString = date == null ? "" : date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String timeString = time == null ? "" : time.format(DateTimeFormatter.ofPattern("hh:mm a"));
        String additional = time != null && date != null ? " " : "";
        return dateString + additional + timeString;
    }

    /**
     * Formats the Date into a file savable format to be read again
     * @return the string to be saved
     */
    public String toSave() {
        String dateString = date == null ? "" : date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String timeString = time == null ? "" : time.format(DateTimeFormatter.ofPattern("HH:mm"));
        return dateString + " " + timeString;
    }
}
