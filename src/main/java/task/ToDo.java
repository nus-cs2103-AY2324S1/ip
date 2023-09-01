package task;

/**
 * A task.ToDo class extends the task class. A Todo task is a task without a specified date to be completed.
 */
public class ToDo extends Task {

    /**
     * Constructs a new Todo task.
     *
     * @param description The name of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a new Todo task.
     * Todo constructed can be completed.
     *
     * @param description The name of the task.
     * @param isDone If task is completed
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a description of the Todo task.
     *
     * @return A string description of the task.ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String fileString() {
        return "T" + super.fileString();
    }
}
