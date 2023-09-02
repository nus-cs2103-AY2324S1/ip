package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {

    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructor to initialise an Event object.
     *
     * @param desc Description of the event.
     * @param start Start date and time of event.
     * @param end End date and time of event.
     */
    public Event(String desc, LocalDateTime start, LocalDateTime end) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    /**
     * Converts the given LocalDateTime into a string of the MMM d yyyy HH:mm format.
     *
     * @param dt The LocalDateTime to be formatted.
     * @return The formatted string representation of the LocalDateTime.
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

        return dateTime.format(dateTimeFormatter);
    }

    /**
     * Converts the Event object to the specific format to be stored in the text file.
     *
     * @return The specific string representation of the Event object to be stored.
     */
    @Override
    public String convertToSavedString() {
        return String.format("[E] %s//%s//%s//", super.convertToSavedString(), 
                formatDateTime(this.start), formatDateTime(this.end));
    }

    /**
     * Converts the Event object to its string representation.
     *
     * @return The string representation of the Event object.
     */
    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(), formatDateTime(this.start),
                formatDateTime(this.end));
    }
}
