/**
 * Encapsulates a Deadline task. Deadline tasks have an end time and description.
 */
public class Deadline extends Task {
    private String endTime;

    /**
     * Constructor for a deadline task.
     *
     * @param endTime     ending time for deadline task
     * @param description task description
     */
    public Deadline(String endTime, String description) {
        super(description);
        this.endTime = endTime;
    }

    /**
     * Returns the string representation of a deadline task with its status.
     *
     * @return String representation of deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.endTime + ")";
    }

    /**
     * Returns the string representation of the deadline task to be stored.
     *
     * @return String representing deadline task to be stored.
     */
    @Override
    public String toStore() {
        return "D | " + super.toStore() + " | " + this.endTime;
    }
}
