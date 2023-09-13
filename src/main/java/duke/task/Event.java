package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event task with a specific start and end date and time.
 *
 * @author Win Sheng
 * @since 3 September 2023
 */
public class Event extends Task {
    public LocalDateTime fromDateTime;
    public LocalDateTime toDateTime;

    /**
     * Constructs a new Event task with the specified description, start and end date and time.
     *
     * @param description The description of the task.
     * @param from The start date and time of the task.
     * @param to The end date and time of the task.
     */
    public Event(String description, String from, String to) {
        super(description);

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-d HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-d"))
                .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy"))
                .toFormatter();

        try {
            //try parse date and time
            this.fromDateTime = LocalDateTime.parse(from, formatter);
            this.toDateTime = LocalDateTime.parse(to, formatter);
        } catch (DateTimeParseException e) {
            try {
                //try parse by just date
                this.fromDateTime = LocalDate.parse(from, formatter).atStartOfDay();
                this.toDateTime = LocalDate.parse(to, formatter).atStartOfDay();
            } catch (DateTimeParseException ex) {
                this.fromDateTime = null;
                this.toDateTime = null;
            }
        }
    }

    /**
     * Gets the type of the Event task.
     *
     * @return The type "[E]" for the Event task.
     */
    @Override
    public String type() {
        return "[E]";
    }

    /**
     * Converts the Event task to a string format for storing in a file.
     *
     * @return A string representation of the Event task for file storage.
     */
    @Override
    public String toFileString() {
        return type() + " | " + (isDone ? "1" : "0") + " | " + description + " | " + fromDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + " | " + toDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        return type() + super.toString() + " (from: " + fromDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma")) + " to " + toDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma")) + ")";
    }
}