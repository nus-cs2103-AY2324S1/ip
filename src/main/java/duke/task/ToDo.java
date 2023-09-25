package duke.task;

/**
 * Subclass of Task class. Stores information about the todo task details.
 */
public class ToDo extends Task {

    /**
     * Class constructor of ToDo.
     * @param details Task description.
     */
    public ToDo(String details) {
        super(details);
        super.setTaskType(TaskType.T.toString());
    }
}
