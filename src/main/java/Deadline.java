/**
 * Represents a task with a specific deadline.
 */
public class Deadline extends Task{
    private String by;

    /**
     * Creates a new deadline task with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(TaskType.DEADLINE, description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A formatted string showing the deadline task's type, status, description, and deadline.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String transformFormat() {
        return super.transformFormat() + " | " + this.by;
    }
}
