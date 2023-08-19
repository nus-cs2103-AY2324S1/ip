package extensions.tasks;

/**
 * A Task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {

    // The deadline of the Deadline task.
    protected String by;

    /**
     * Constructor for a Deadline task.
     *
     * @param description The description of the Deadline task.
     * @param by The deadline of the Deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the String representation of the Deadline task.
     *
     * @return String representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
