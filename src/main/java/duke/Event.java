package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task that occurs within a specified date and time range.
 * Extends the base class Task.
 */
public class Event extends Task {

    protected LocalDate fromDate;
    protected LocalTime fromTime;
    protected LocalDate toDate;
    protected LocalTime toTime;

    protected String from;
    protected String to;

    /**
     * Constructs an event task with description and date-time range.
     *
     * @param description The description of the event task.
     * @param isDone      Indicates whether the task is done or not.
     * @param from        The starting date and time of the event.
     * @param to          The ending date and time of the event.
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        parseDateTime(from, to);
        this.from = from;
        this.to = to;
    }

    /**
     * Checks if a given date-time string is in a valid format.
     *
     * @param dateTimeString The date-time string to be checked.
     * @return True if the date-time string is in a valid format, otherwise false.
     */
    public boolean isValidDate(String dateTimeString) {
        try {
            String[] parts = dateTimeString.split(" ");
            LocalDate.parse(parts[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalTime.parse(parts[1], DateTimeFormatter.ofPattern("HHmm"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Parses the given date-time strings into LocalDate and LocalTime objects.
     *
     * @param fromdateTime The starting date-time string of the event.
     * @param todateTime   The ending date-time string of the event.
     */
    public void parseDateTime(String fromdateTime, String todateTime) {
        if (isValidDate(fromdateTime) && isValidDate(todateTime)) {
            String[] fromparts = fromdateTime.split(" ");
            fromDate = LocalDate.parse(fromparts[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            fromTime = LocalTime.parse(fromparts[1], DateTimeFormatter.ofPattern("HHmm"));

            String[] toparts = todateTime.split(" ");
            toDate = LocalDate.parse(toparts[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            toTime = LocalTime.parse(toparts[1], DateTimeFormatter.ofPattern("HHmm"));
        }
    }

    /**
     * Generates a string representation of the event task.
     *
     * @return A formatted string describing the event task, including date and
     *         time.
     */
    @Override
    public String toString() {
        if (fromDate != null && toDate != null) {
            return "[E]" + super.toString() + " (from: " + fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " " + fromTime.format(DateTimeFormatter.ofPattern("h:mm a")) + " to: "
                    + toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " " + toTime.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
        } else {
            return "[E]" + super.toString() + " (from: " + from + " to: "
                    + to + ")";
        }

    }

    /**
     * Converts the event task to a string format suitable for saving.
     *
     * @return A string representation of the event task for saving purposes.
     */
    @Override
    public String toSaveString() {
        if (fromDate != null && toDate != null) {
            return "E," + isDone + "," + description + "," + fromDate + " " + fromTime + "," + toDate + " " + toTime;
        } else {
            return "E," + isDone + "," + description + "," + from + "," + to;
        }
    }
}