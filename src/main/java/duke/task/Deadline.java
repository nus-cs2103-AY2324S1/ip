package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class encapsulates a Deadline task.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Constructor method for a Deadline.
     * @param taskDescription Description of the task
     * @param deadline Deadline for the task
     */
    public Deadline(String taskDescription, LocalDate deadline) {
        super(taskDescription);
        this.deadline = deadline;
    }

    /**
     * Retrieve the deadline of the Deadline task.
     * @return A String representation of the deadline of the Deadline.
     */
    public String getDeadline() {
        return this.deadline.toString();
    }

    public String getFormattedDeadline() {
        return deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        assert (deadline != null) : "The deadline of a Deadline task cannot be null.";

        return "[D]" + super.toString() + " (by: " + this.getFormattedDeadline() + ")";
    }
}
