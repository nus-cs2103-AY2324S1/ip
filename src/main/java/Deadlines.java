/**
 * A class for Deadlines tasks.
 */
public class Deadlines extends Task{
    /** Class field by that tells when the deadline is due. */
    protected String by;

    /**
     * Constructor to initialize the Deadlines class.
     * @param description Describes the deadline.
     * @param by When the task is due.
     */
    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Method that overrides default toString.
     * @return String representation of Deadlines.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), this.description, this.by);
    }
}
