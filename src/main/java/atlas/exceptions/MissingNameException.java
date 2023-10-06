package atlas.exceptions;

import atlas.tasks.Task;

/**
 * Exception for when a task does not have a name
 */
public class MissingNameException extends RuntimeException {
    private final Task.Type taskType;

    /**
     * Constructor for a MissingNameException
     * @param taskType The task type of the task missing a name
     */
    public MissingNameException(Task.Type taskType) {
        this.taskType = taskType;
    }

    @Override
    public String getMessage() {
        return "You know, everything must have a name, and so does " + taskType;
    }
}
