public class Deadline extends Task {

    protected String by;

    /**
     * Constructor for Deadline.
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Overrides the toString() method in Task.
     * @return the string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}