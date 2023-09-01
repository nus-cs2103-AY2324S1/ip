/**
 * Represent a task that need to be done before a specific date/time.
 */
public class Deadline extends Task {
    protected String deadline;

    /**
     * Creates a deadline task that is initially undone.
     *
     * @param description The description of the task that the user inputs
     * @param deadline The deadline that the user inputs
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toSaveFormat() {
        return "D | " + super.toSaveFormat() + " | " + this.deadline;
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.description +
                " (by: " + this.deadline + ")";
    }
}
