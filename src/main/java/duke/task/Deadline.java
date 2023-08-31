package duke.task;

import java.time.LocalDate;

/**
 * Represents a task with a given deadline.
 */
public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return String representation of the Deadline object.
     */
    @Override
    public String toString() {
        String status = "[" + (super.isDone ? "X" : " ") + "]";
        String deadline = "(by: " + this.by + ")";
        return "[D]" + status + " " + super.description + " " + deadline;
    }
}
