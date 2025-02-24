package tasket.task;

/**
 * The class for deadline.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a deadline.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a deadline.
     * This version includes tags.
     *
     * @param description The description of the task.
     * @param tags The tags of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by, String[] tags) {
        super(description, tags);
        this.by = by;
    }

    /**
     * {@inheritDoc}
     *
     * @return The string format of the task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s) %s", super.toString(), this.by, this.getTags()).trim();
    }

    /**
     * {@inheritDoc}
     *
     * @return The save format of the task.
     */
    @Override
    public String toSaveString() {
        return String.format("D | %s | %s %s", super.toSaveString(), this.by, this.getTags()).trim();
    }
}
