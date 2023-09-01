package duke;

/**
 * Represents an executable task that can be executed to perform an action.
 */
public interface Executable {
    /**
     * Executes the task and returns a response message.
     *
     * @return A response message indicating the result of executing the task.
     */
    String execute();
}
