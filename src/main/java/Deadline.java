/**
 * Encapsulates the Deadline class. Inherits the Task
 * class and adds on additional unique features.
 *
 * @author Tan Kerway
 */
public class Deadline extends Task {
    // deadline for the deadline task
    private final String by;

    /**
     * Constructor for the Deadline class
     *
     * @author Tan Kerway
     * @param description the description of the task
     * @param by the deadline for the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the String representation of a Deadline class
     *
     * @author Tan Kerway
     * @return the String representation of a Deadline class
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
