/**
 * Represents a task containing a description, and the due time.
 */
public class Deadline extends Task {

    protected String by;

    protected Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}