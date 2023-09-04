package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline, including the description and the due date.
 */
public class Deadline extends Task {

    /**
     * The due date of the task.
     */
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with the given description and due date.
     *
     * @param description The description of the task.
     * @param by          The due date of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        String newDate = by.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
        return "[D]" + super.toString() + " (by: " + newDate + ")";
    }
}

