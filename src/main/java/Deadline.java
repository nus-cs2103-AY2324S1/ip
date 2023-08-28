/**
 * Represents a deadline.
 *
 * @author Pearlynn
 */

public class Deadline extends Task{

    /**
     * The deadline of the task.
     */
    protected String by;

    /**
     * Constructor for Deadline class.
     *
     * @param description The description of the deadline.
     * @param by The deadline of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for Deadline class.
     *
     * @param description The description of the deadline.
     * @param isDone The status of the deadline.
     * @param by The deadline of the deadline.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline in the file.
     *
     * @return A string representation of the deadline in the file.
     */
    @Override
    public String taskStringify() {
        int status = super.isDone ? 1 : 0;
        return "D | " + status + " | " + super.description + " | " + this.by;
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return A string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
