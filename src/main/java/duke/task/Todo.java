package duke.task;

/**
 * The Todo class represents a simple task.
 */
public class Todo extends Task{
    /**
     * Constructs a Todo task with a description and completion status.
     *
     * @param task The description of the task.
     * @param done The completion status of the task.
     */
    public Todo(String task, boolean done) {
        super(task, done);
    }

    /**
     * Returns a string representation of the Todo task for Ui display.
     *
     * @return A formatted string including the task type and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the Todo task for saving to a file.
     *
     * @return A formatted string suitable for saving to a file.
     */
    @Override
    public String getTaskFileString() {
        return "T" + " , " + super.getTaskFileString();
    }
}
