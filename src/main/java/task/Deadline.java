package task;

/**
 * Represents a task that needs to be done before a specific date/time.
 *
 * @author Andrew Daniel Janong
 */
public class Deadline extends Task {
    /**
     * The end time or due date of the deadline.
     */
    protected String endTime;

    /**
     * A public constructor for the task.Deadline.
     * @param description
     * @param endTime
     */
    public Deadline(String description, String endTime) {
        super(description);
        this.endTime = endTime;
    }

    /**
     * A string representation of a task.Deadline.
     * Uses an extra [D] to represent a task.Deadline and the due date.
     * @return the string representation of the task.Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.endTime + ")";
    }
}
