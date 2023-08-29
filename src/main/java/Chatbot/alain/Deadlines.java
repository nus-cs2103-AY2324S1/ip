package chatbot.alain;

/**
 * Represents a task with a specific deadline.
 */
public class Deadlines extends Task {

    /**
     * The deadline time associated with the task.
     */
    protected String byTime;

    /**
     * Constructs a Deadline task.
     *
     * @param description Description of the task.
     * @param by The deadline time of the task.
     */
    public Deadlines(String description, String by) {
        super(description);
        this.byTime = by;
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byTime + ")";
    }
}
