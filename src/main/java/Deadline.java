/**
 * Represents an deadline task in the Duke application.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a new Deadline task.
     *
     * @param description The description of the event.
     * @param by The deadline of the event.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}