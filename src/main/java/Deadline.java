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
     * Returns the string representation of the deadline.
     *
     * @return A string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
