/**
 * A deadline class extends the task class. A deadline task represents a task that has to be
 * completed by a certain date.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a deadline task with specified name
     *
     * @param description The name of the task.
     * @param by The date the task needs to be completed.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the description of the deadline task with the specified date that task needs to be completed.
     *
     * @return The string description of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
