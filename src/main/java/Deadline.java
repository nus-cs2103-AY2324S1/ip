/**
 * Represents a Deadline object.
 */
public class Deadline extends Task {
    /**
     * End time of deadline.
     */
    protected String by;

    /**
     * Constructor to create a Deadline object.
     * @param description Description of event
     * @param by End time of event
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a String representation of a Deadline.
     * @return String representation of a Deadline.
     */
    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + super.toString() + " (by: " + this.by + ")";
    }
}