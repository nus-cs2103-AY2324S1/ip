/**
 * A task that has a deadline.
 */
public class Deadline extends Task{
    private String deadline;

    /**
     * Creates a deadline task instance.
     *
     * @param description Description of the task.
     * @param deadline Deadline of the task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return Desired string representation of the task.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.deadline + ")";
    }

    /**
     * Returns a string representation of the task to be inserted into a file.
     *
     * @return Desired string representation of the task.
     */
    @Override
    public String toStringInFile() {
        return "[D]" + super.toStringInFile() + " " + this.deadline;
    }
}
