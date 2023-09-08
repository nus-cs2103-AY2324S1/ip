/**
 * Represents a Deadline task
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor for Deadline.
     * 
     * @param description The description of the deadline task.
     * @param isDone      Whether the deadline task is done.
     * @param by          The deadline of the deadline task.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the description and deadline of the deadline task, to output to the user.
     * 
     * @return A String, properly formatting the description and deadline of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns the isDone, description, and deadline of the deadline task, to input into the tasks.txt file.
     * 
     * @return A String, properly formatting the description of the deadline task.
     */
    public String toFileString() {
        // Convert task to file format string
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

}
