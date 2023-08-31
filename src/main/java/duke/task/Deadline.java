package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The duke.task.Deadline class represents a duke.task of type "duke.task.Deadline" inherited from the duke.task.Task class.
 * It contains a description and a deadline (by when the duke.task should be completed).
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a new duke.task.Deadline duke.task with the provided description and deadline.
     *
     * @param description The description of the duke.task.Deadline duke.task.
     * @param by          The deadline for completing the duke.task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toFileString() {
        String status = getStatusIcon().equals("X") ? "1" : "0";
        return "D" + " | " + status + " | " + super.description + " | " + by;
    }


    /**
     * Returns a string representation of the duke.task.Deadline duke.task, including its completion status, description, and deadline.
     *
     * @return A formatted string indicating the duke.task type, completion status, and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
