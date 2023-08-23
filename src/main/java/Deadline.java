/**
 * Represents a task with a specific deadline.
 * This class extends the Task class and adds a deadline to it.
 */
public class Deadline extends Task {
    /**
     * The deadline for this task.
     */
    protected String by;

    /**
     * Constructs a Deadline object with the given description and deadline.
     *
     * @param description The description of the task.
     * @param by The deadline for the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return A formatted string including the task type, description, and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}