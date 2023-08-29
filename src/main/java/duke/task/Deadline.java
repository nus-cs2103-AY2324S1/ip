package duke.task;

/**
 * The Deadline class represents a task with a specific due date.
 * It inherits from the Task class and adds a due date field.
 */
public class Deadline extends Task {
    /**
     * The due date of the task.
     */
    private final String by;

    /**
     * Constructs a new Deadline object with the specified description, completion status, and due date.
     *
     * @param description The description of the Deadline task.
     * @param isDone      The completion status of the Deadline task.
     * @param deadline    The due date of the task.
     */
    public Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.by = deadline;
    }

    /**
     * Constructs a new Deadline object with the specified description and due date.
     * The deadline is initially marked as not done.
     *
     * @param description The description of the Deadline task.
     * @param deadline    The due date of the task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.by = deadline;
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A formatted string indicating the task type, completion status, description, and due date.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.by + ")";
    }

    /**
     * Returns a formatted string for writing the Deadline task to a file.
     *
     * @return A formatted string containing task details for file storage.
     */
    @Override
    public String writeFile() {
        return "D | " + super.writeFile() + " | " + this.by;
    }
}
