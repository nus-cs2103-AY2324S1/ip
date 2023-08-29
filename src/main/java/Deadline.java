public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the time when the deadline is due.
     * 
     * @return Time when the deadline is due.
     */
    public String getCompleteBy() {
        return this.by;
    }

    /**
     * Returns a string representation of Deadline
     *
     * @return A string representation of Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
