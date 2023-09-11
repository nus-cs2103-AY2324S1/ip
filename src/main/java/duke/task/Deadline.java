package duke.task;

import duke.util.Formatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task, containing a description and a deadline.
 */
public class Deadline extends Task {

    private LocalDateTime deadline;

    /**
     * Returns a new Deadline task containing the message and the deadline date.
     *
     * @param message The description for the deadline.
     * @param deadline The deadline, as a LocalDateTime.
     */
    public Deadline(String message, LocalDateTime deadline) {
        super(message);
        this.deadline = deadline;
    }

    /**
     * Returns a String containing information within the Deadline task, formatted to be saved.
     *
     * @return The deadline, formatted as a String to be saved in the save file.
     */
    public String toSaveFormatString() {
        return "D | " + getStatusNumber() + " | " + message + " | " + deadline;
    }

    /**
     * Returns a String representation of the Deadline task, formatted for output in the application.
     *
     * @return The deadline, formatted as a String for output in the application.
     */
    public String toString() {
        return "[D]" + getStatusIcon() + " " + message
                + " (by: " + Formatter.formatDateTime(deadline) + ")";
    }
}
