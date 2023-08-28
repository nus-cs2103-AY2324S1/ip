/**
 * Represents a task of type Deadline
 *
 * @author Celestine
 */
public class Deadline extends Task {

    protected String by;

    /**
     * A constructor for a task of type Deadline
     *
     * @param description the task details
     * @param by the deadline for the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    public String toFileFormat() {
        return "D | " + this.isDone + " | " + this.description + " | " + this.by;
    }
}

