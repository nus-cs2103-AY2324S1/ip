package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task that has a description and a due date/time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor with description and due date/time.
     *
     * @param description The description of the deadline.
     * @param by The due date/time of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = convertDateTime(by);
    }

    /**
     * Converts the string by to type LocalDateTime.
     *
     * @param dateTimeString String by to be converted.
     * @return LocalDateTime by.
     */
    private LocalDateTime convertDateTime(String dateTimeString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("☹ OOPS!!! Invalid date/time format. Please use dd/MM/yyyy HHmm");
        }
    }

    /**
     * Formats the LocalDateTime by to desired string.
     *
     * @param by LocalDateTime by to be formatted.
     * @return Formatted string to show due date/time.
     */
    private String formatDateTime(LocalDateTime by) {
        //  "a" represents the AM/PM marker
        return by.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a"));
    }

    /**
     * Returns the due date/time of the deadline.
     *
     * @return The due date/time.
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Returns a string representation of the duke.task.Deadline object.
     *
     * @return A string representation of the duke.task.Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDateTime(by) + ")";
    }
}
