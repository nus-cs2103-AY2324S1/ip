package extensions.tasks;

/**
 * A Task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {

    // The deadline of the Deadline task.
    protected final String by;

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
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }

    /**
     * Exports the Deadline task to a String to be saved.
     *
     * @return String representation of the Deadline task to be saved.
     */
    @Override
    public String export() {
        return String.format("DEADLINE || %s || %s || %s || %s", super.export(), this.by, "", "");
    }
}
