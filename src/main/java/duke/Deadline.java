package duke;

/**
 * Categorises task as deadline.
 */
public class Deadline extends Task {
    protected String by;

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
