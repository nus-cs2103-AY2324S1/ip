package duke;

/**
 * Categorises task as deadline.
 */
public class Deadline extends Task {

    /** String to hold deadline of a task */
    protected String by;

    /**
     * To create a new task categorised as deadline
     *
     * @param description Description of the task
     * @param by Deadline of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    /**
     * Returns string representation of the deadline task in the
     * format that will be outputted to the user.
     *
     * @return String representation of deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}
