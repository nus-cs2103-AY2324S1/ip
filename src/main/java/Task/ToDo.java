package task;

/**
 * The `ToDo` class represents a task without a specific deadline or event time in the BloopBot application.
 * It extends the base `Task` class and inherits its properties and methods.
 * A `ToDo` task only has a description and can be marked as completed or not completed.
 *
 * @author raydenlim
 * @version 0.0.0
 */
public class ToDo extends Task {
    /**
     * Constructs a new `ToDo` task with the specified description.
     *
     * @param taskDesc The description of the `ToDo` task.
     */
    public ToDo(String taskDesc) {
        super(taskDesc);
    }
}
