public class DeadlineTask extends Task {
    private final String by;

    /**
     * Constructor for DeadlineTask.
     *
     * @param description of the task.
     * @param by          deadline of the task.
     */
    public DeadlineTask(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Get the deadline of the task.
     *
     * @return deadline of the task.
     */
    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.getBy() + ")";
    }
}
