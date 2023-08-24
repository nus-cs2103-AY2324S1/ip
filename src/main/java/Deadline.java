/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Creates a deadline object. This is the main constructor of the Deadline class.
     * @param description Description of deadline.
     * @param isDone Whether the deadline is done.
     * @param by Date/time of deadline.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Creates a deadline object. This calls the main constructor when the default for isDone is false.
     * @param description Description of deadline.
     * @param by Date/time of deadline.
     */
    public Deadline(String description, String by) {
        this(description, false, by);
    }

    /**
     * Returns the string representation of the deadline task.
     * @return string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a string representation of the deadline to be used in storage.
     * @return string representation of the deadline for storage.
     */
    @Override
    public String storageTaskRep() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
