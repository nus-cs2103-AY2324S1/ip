package duke.task;

import java.time.LocalDate;

/**
 * Represents a task with a given deadline.
 */
public class Deadline extends Task {
    protected LocalDate byDate;

    public Deadline(String description, LocalDate byDate) {
        super(description);
        this.byDate = byDate;
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
