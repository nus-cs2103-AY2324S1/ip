package DukeTasks;

/**
 * Encapsulates the DukeTasks.Deadline class. Inherits the DukeTasks.Task
 * class and adds on additional unique features.
 *
 * @author Tan Kerway
 */
public class Deadline extends Task {
    // deadline for the deadline task
    private final String by;

    /**
     * Constructor for the DukeTasks.Deadline class.
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
     * Constructor for the DukeTasks.Deadline class.
     *
     * @author Tan Kerway
     * @param description the description of the task
     * @param isDone whether the task is done
     * @param by the deadline for the task
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the deadline of the DukeTasks.Deadline instance.
     *
     * @author Tan Kerway
     * @return the deadline of the DukeTasks.Deadline instance
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Returns the String representation of a DukeTasks.Deadline class.
     *
     * @author Tan Kerway
     * @return the String representation of a DukeTasks.Deadline class
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
