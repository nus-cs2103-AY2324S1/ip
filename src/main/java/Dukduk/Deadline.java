package dukduk;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Initialises a new Deadline task with the given description and deadline.
     *
     * @param description The description of the task.
     * @param by          The deadline of the task as a LocalDateTime object.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets the LocalDateTime representing the deadline of the task.
     *
     * @return The LocalDateTime representing the deadline.
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Returns a string representation of the Deadline task, including its description and deadline.
     *
     * @return A formatted string representing the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**
     * Returns a string representation of the Deadline task in a data-friendly format.
     *
     * @return A formatted string suitable for data storage.
     */
    @Override
    public String toDataString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + 
                by.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }
}
