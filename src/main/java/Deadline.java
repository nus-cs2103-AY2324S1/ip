/**
 * Represents a task that need to be done before a specific date/time.
 * This class is a subclass of the Task class and inherits its properties and methods.
 */
public class Deadline extends Task {

    /**
     * The deadline for the task.
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
     * Return a string representation of the Deadline task.
     *
     * @return The string representation of the Deadline task with description and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
