public class Deadline extends Task {

    protected String by;

    /**
     * Constructor for the deadline task
     * @param description Description of the task
     * @param by Deadline of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
