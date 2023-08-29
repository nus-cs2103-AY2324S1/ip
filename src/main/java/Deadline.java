/**
 * Represents a task that has a deadline.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
 */
public class Deadline extends Task {
    /** A string indicating deadline of task */
    protected String by;

    /**
     * A constructor to initialize the Deadline class.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the Deadline that will
     * be displayed to the user in the list.
     *
     * @return The string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    /**
     * Returns the string representation of the task to be stored in a local file.
     *
     * @return The storage string representation of the task.
     */
    public String toStorageString() {
        return "D, " + isDone + ", " + description + ", " + by;
    }
}
