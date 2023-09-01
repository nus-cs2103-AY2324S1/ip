package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task of type "Deadline" inherited from the Task class.
 * It contains a description and a deadline (by when the task should be completed).
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a new Deadline task with the provided description and deadline.
     *
     * @param description The description of the Deadline task.
     * @param by          The deadline for completing the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a new Deadline task with the provided description, deadline, and completion status.
     *
     * @param description The description of the Deadline task.
     * @param by          The deadline for completing the task.
     * @param isDone      The completion status of the task.
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Converts the Deadline task to a formatted string suitable for saving to a file.
     *
     * @return A string representation of the Deadline task in a format compatible with file storage.
     */
    @Override
    public String toFileString() {
        String status = isDone ? "1" : "0"; // Using isDone directly for readability
        return "D | " + status + " | " + description + " | " + by;
    }

    /**
     * Returns a string representation of the Deadline task, including its completion status, description, and deadline.
     *
     * @return A formatted string indicating the task type, completion status, and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
