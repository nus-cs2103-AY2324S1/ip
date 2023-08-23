/**
 * Represents a Task with a deadline.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for a Deadline instance.
     *
     * @param description The description of the underlying task.
     * @param by The date by which the task is due.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Provides the string representation of the Deadline instance.
     * @return A string with the relevant information of the Deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
