/**
 * Encapsulates a task with a deadline.
 * Inherits from Task class.
 * @author Wu Jingya
 */
public class DeadlineTask extends Task{
    private String by;

    /**
     * Creates a Deadline Task with the specified description and deadline
     * @param description The task's description
     * @param by The task's deadline
     */
    public DeadlineTask(String description, String by) {
        super(description);
        this.by = by;
    }

    public DeadlineTask(String description, String by, Boolean done) {
        super(description, done);
        this.by = by;
    }

    /**
     * Returns the string representation of the task
     * @return The string representation of the task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toData() {
        return "D|" + super.toData() + "|" + by;
    }
}
