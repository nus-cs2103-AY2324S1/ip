/**
 * A class which inherits from Task class.
 * Represents a task with deadline but no start time.
 */
public class Deadline extends Task {

    /** The due date of this task. */
    protected String endTime;

    /**
     * Initialises using the given description and end time.
     * @param description The name of this deadline task.
     * @param endTime The deadline of task.
     */
    public Deadline(String description, String endTime) {
        super(description);
        this.endTime = endTime;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return Returns a string describing this deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + endTime + ")";
    }
}
