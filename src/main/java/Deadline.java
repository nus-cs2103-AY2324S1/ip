public class Deadline extends Task{
    protected String by;

    /**
     * Constructor for the Deadline class
     *
     * @param description the name/description of the deadline
     * @param by the due date for this task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getBy() {
        return by;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Returns the string representation of this deadline, including its type of task, completion status,
     * description, and due date
     *
     * @return the string representation of the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
