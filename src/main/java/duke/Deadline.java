package duke;

/**
 * The duke.Deadline class represents a task with a deadline.
 * It extends the duke.Task class.
 * Adds a 'by' field to store the date of the deadline.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for duke.Deadline class.
     *
     * @param description the description of the task
     * @param by          the deadline of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Overrides toString method from duke.Task
     * returns a String representation of duke.Deadline task.
     *
     * @return The String representation of duke.Deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
