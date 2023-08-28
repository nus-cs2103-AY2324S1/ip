/**
 * A Deadline is a task with an end date/time.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Deadline constructor method.
     * @param description A description for the deadline task.
     * @param by When the deadline should be completed by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
    public String getBy() {
        return this.by;
    }
}
