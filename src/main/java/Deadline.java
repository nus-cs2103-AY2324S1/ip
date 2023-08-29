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
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
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

    @Override
    public String toSaveFormat() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + super.toString() + " | " + this.by;
    }
}