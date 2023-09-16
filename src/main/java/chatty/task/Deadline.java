package chatty.task;

/**
 * A class that represents a Deadline task.
 */
public class Deadline extends Task {

    private final String deadline;

    /**
     * Constructor for the Deadline class that calls the constructor of the parent class to create a Task object.
     *
     * @param taskDescription The deadline task to be added.
     * @param deadline        The deadline of the task to be added.
     */
    public Deadline(String taskDescription, String deadline) {
        super(taskDescription);
        assert deadline != null : "Deadline should not be null when creating a Deadline task.";
        this.deadline = deadline;
    }

    /**
     * Returns the String representation of the object.
     *
     * @return The String representation of the Deadline task.
     */
    @Override
    public String toString() {
        String status = (this.isDone) ? "[D][X] " : "[D][ ] ";
        String task = String.format("%s (by: %s)", this.task, this.deadline);
        return status + task;
    }
}
