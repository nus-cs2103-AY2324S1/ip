package duke.task;

/**
 * Subclass of Task class. Stores information about the todo task details.
 */
public class ToDo extends Task {
    public ToDo(String details) {
        super(details);
        super.setTaskType(TaskType.T.toString());
    }
}
