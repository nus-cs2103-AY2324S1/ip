package duke.tasks;

import java.time.LocalDate;

/**
 * Encapsulates a Deadline task. Deadline tasks have an end time and description.
 */
public class Deadline extends Task implements Comparable<Deadline> {
    private LocalDate endDate;

    /**
     * Constructor for a deadline task.
     *
     * @param endDate     The deadline for deadline task
     * @param description task description
     */
    public Deadline(LocalDate endDate, String description) {
        super(description);
        this.endDate = endDate;
    }

    /**
     * Returns the string representation of a deadline task with its status.
     *
     * @return String representation of deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.endDate + ")";
    }

    /**
     * Returns the string representation of the deadline task to be stored.
     *
     * @return String representing deadline task to be stored.
     */
    @Override
    public String tasktoString() {
        return "D | " + super.tasktoString() + " | " + this.endDate;
    }

    /**
     * Specifies the natural ordering of deadline tasks.
     *
     * @param deadline the object to be compared.
     * @return A negative integer, zero, or a positive integer as this
     *         deadline is less than, equal to, or greater than the specified deadline.
     */
    @Override
    public int compareTo(Deadline deadline) {
        return this.endDate.compareTo(deadline.endDate);
    }
}
