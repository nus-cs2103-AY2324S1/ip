package duke;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Deadline constructor.
     * @param description Deadline name shown to user.
     * @param by Deadline. A string, no type checking done.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Gives string representation of the task, shown to users.
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
