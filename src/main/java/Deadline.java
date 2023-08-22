/**
 * This class is a subclass of Task which defines tasks with deadline.
 */
public class Deadline extends Task {
    /** A String that indicates deadline of task. */
    private String by;

    /**
     * A constructor for tasks with deadline
     * @param description Name of task
     * @param by Deadline of task to be completed by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     *
     * @return String representation of task with deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}
