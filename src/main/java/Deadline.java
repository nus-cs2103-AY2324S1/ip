
/**
 * Represents a deadline task, which is a task that need to be done before a specific date/time.
 */
public class Deadline extends Task {
    private String by;


    /**
     * Constructs a Deadline object with the specified description and deadline time.
     *
     * @param description The description of the deadline task.
     * @param by          The due date/time for the deadline task.
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
