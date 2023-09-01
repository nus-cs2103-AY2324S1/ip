package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    /**
     * The deadline of the task.
     */
    protected LocalDateTime deadline;

    /**
     * Constructs a new Deadline object.
     *
     * @param description The description of the task.
     * @param deadline The deadline of the task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.deadline = LocalDateTime.parse(deadline, formatter);
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return String representation of the Deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        return "[D]" + super.toString() + " (by: " + this.deadline.format(formatter) + ")";
    }

    /**
     * Returns the string representation of the Deadline task to be saved in the file.
     *
     * @return String representation of the Deadline task to be saved in the file.
     */
    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D" + super.toFileString() + " | " + this.deadline.format(formatter);
    }
}
