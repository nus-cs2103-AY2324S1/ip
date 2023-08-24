/**
 * Represents a deadline task that has a description and a due date/time.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor with description and due date/time.
     *
     * @param description The description of the deadline.
     * @param by The due date/time of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return A string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
