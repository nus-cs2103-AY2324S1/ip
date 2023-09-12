package duke.task;

import java.time.LocalDate;

/**
 * Represents a task with a given deadline.
 */
public class Deadline extends Task {
    protected LocalDate byDate;

    /**
     * Constructor for Deadline class.
     *
     * @param description The given description for the deadline.
     * @param byDate The given deadline.
     */
    public Deadline(String description, LocalDate byDate) {
        super(description);
        this.byDate = byDate;

        assert this.byDate != null : "byDate of the Deadline cannot be null";
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return String representation of the Deadline object.
     */
    @Override
    public String toString() {
        String status = "[" + (super.isDone ? "X" : " ") + "]";
        String deadline = "(by: " + this.byDate + ")";
        return "[D]" + status + " " + super.description + " " + deadline;
    }
}
