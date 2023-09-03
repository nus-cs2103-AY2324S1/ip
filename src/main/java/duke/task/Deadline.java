package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task with a specific deadline.
 *
 * @author Win Sheng
 * @since 3 September 2023
 */
public class Deadline extends Task {

    public LocalDateTime dateTime;

    /**
     * Constructs a new Deadline task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))
                .toFormatter();

        try {
            this.dateTime = LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException e) {
            this.dateTime = null;
            System.out.println("Please use the following formats:\n"
                    + "deadline task /by yyyy-mm-dd hhmm\n"
                    + "deadline task /by dd/mm/yyyy hhmm");
        }
    }

    /**
     * Gets the type of the Deadline task.
     *
     * @return The type "[D]" for the Deadline task.
     */
    @Override
    public String type() {
        return "[D]";
    }

    /**
     * Converts the Deadline task to a string format for storing in a file.
     *
     * @return A string representation of the Deadline task for file storage.
     */
    @Override
    public String toFileString() {
        return type() + " | " + (isDone ? "1" : "0") + " | " + description + " | " + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return type() + super.toString() + " (by: " + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma")) + ")";
    }
}