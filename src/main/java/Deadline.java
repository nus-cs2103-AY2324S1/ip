/**
 * Encapsulates a Deadline task that can be added to the task manager.
 *
 * @author Teo Kai Sheng
 */
public class Deadline extends Task {

    /**
     * Deadline of the task.
     */
    protected String by;

    /**
     * Constructor to create a Deadline.
     * @param description Description of the deadline.
     * @param by Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the String representation of the deadline.
     * @return A String representing the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns the String representation of the deadline to be saved in the hard disk.
     * @return A String representing the deadline.
     */
    @Override
    public String taskToString() {
        return "D | " + super.taskToString() + " | " + by;
    }
}