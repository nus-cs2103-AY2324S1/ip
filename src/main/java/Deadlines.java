/**
 * A class for Deadlines tasks.
 */
public class Deadlines extends Task{
    /** Class field by that tells when the deadline is due. */
    protected String by;

    /**
     * Constructor to initialize the Deadlines class.
     *
     * @param description Describes the deadline.
     * @param by When the task is due.
     */
    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * {@inheritDoc}
     */
    public String toFileString() {
        String doneStatus = isDone ? "1" : "0";
        return String.format("D | %s | %s | %s", doneStatus, this.description, this.by);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), this.description, this.by);
    }
}
