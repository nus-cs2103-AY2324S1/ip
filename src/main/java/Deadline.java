public class Deadline extends Task{
    /** String deadline for Deadline. */
    protected String by;

    /** Constructor for Deadline.
     *
     * @param description Description of task.
     * @param by Deadline for Deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /** toString method for Deadline.
     *
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
