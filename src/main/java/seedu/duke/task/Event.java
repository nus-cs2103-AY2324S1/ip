package seedu.duke.task;

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
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))
                .toFormatter();

        try {
            this.fromDateTime = LocalDateTime.parse(from, formatter);
            this.toDateTime = LocalDateTime.parse(to, formatter);
        } catch (DateTimeParseException e) {
            this.fromDateTime = null;
            this.toDateTime = null;
            System.out.println("Please use the following formats:\n"
                    + "event task /from yyyy-mm-dd hhmm /to yyyy-mm-dd hhmm\n"
                    + "deadline task /from dd/mm/yyyy hhmm /to dd/mm/yyyy hhmm");
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