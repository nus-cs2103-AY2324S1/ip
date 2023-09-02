package duke.tasks;

/**
 * Represents a 'To-Do' task in the Duke application.
 */
public class TodoTask extends Task {

    /**
     * Constructs a TodoTask.
     *
     * @param taskName The name of the task.
     */
    public TodoTask(String taskName) {
        super(taskName, TaskType.TODO);
    }
}