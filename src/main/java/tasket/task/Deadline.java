package tasket.task;

/**
 * The class for deadline.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * The constructor of deadline
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * @inheritDocs
     *
     * @return The string format of the task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }

    /**
     * @inheritDocs
     *
     * @return The save format of the task.
     */
    @Override
    public String toSaveString() {
        return String.format("D | %s | %s", super.toSaveString(), this.by);
    }
}
