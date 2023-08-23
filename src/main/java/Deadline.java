
/**
 * Represents a deadline task that has a description and a due date.
 */
public class Deadline extends Task {

    /** The due date of the deadline task. */
    protected String by;

    /**
     * Constructs a deadline task with a description and a due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date of the deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " ( by: " + by + ")";
    }
}
