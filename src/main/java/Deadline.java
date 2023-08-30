/**
 * The Deadline class represents a task of type "Deadline" inherited from the Task class.
 * It contains a description and a deadline (by when the task should be completed).
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a new Deadline task with the provided description and deadline.
     *
     * @param description The description of the Deadline task.
     * @param by          The deadline for completing the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toFileString() {
        String status = getStatusIcon().equals("X") ? "1" : "0";
        return "D" + " | " + status + " | " + super.description + " | " + by;
    }


    /**
     * Returns a string representation of the Deadline task, including its completion status, description, and deadline.
     *
     * @return A formatted string indicating the task type, completion status, and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
