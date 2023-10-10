package duke;

/**
 * Class to handle tasks of type todo.
 */
public class ToDo extends Task {

    /**
     * Initialises a task of type todo.
     *
     * @param description task description.
     * @param taskType type of task.
     */
    public ToDo(String description, char taskType) {
        super(description, taskType);
    }
}
