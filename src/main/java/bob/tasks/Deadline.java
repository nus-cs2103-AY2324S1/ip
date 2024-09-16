package bob.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task that contains a description and a date for the deadline.
 */
public class Deadline extends Task {

    protected LocalDate dueDate;

    /**
     * Constructor for the Deadline class.
     *
     * @param description the name/description of the deadline.
     * @param dueDate the due date for this task.
     */
    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String getBy() {
        return dueDate.toString();
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Changes the due date of a Deadline
     *
     * @param newDueDate the new due date of the deadline
     */
    public void rescheduleDueDate(LocalDate newDueDate) {
        this.dueDate = newDueDate;
    }

    /**
     * Returns the string representation of this deadline, including its type of task, completion status,
     * description, and due date.
     *
     * @return the string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
