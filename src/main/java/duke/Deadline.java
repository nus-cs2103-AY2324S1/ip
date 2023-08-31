package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task. A <code>Deadline</code> corresponds to a Task
 * which has a description, deadline, and stores information whether this task
 * has been completed.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private DateTimeFormatter formatterSave = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    /**
     * Constructs a new instance of a Deadline task.
     *
     * @param description Name of the deadline task.
     * @param deadline Date before which task should be completed.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructs a new instance of a Deadline task.
     *
     * @param description Name of the deadline task.
     * @param deadline Date before which task should be completed.
     * @param isDone {@code true} if the task is marked as done, {@code false} otherwise.
     */
    public Deadline(String description, LocalDateTime deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public String toFileString() {
        return "D" + super.toFileString() + " | " + this.deadline.format(formatterSave);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + deadline.format(formatter) + ")";
    }
}