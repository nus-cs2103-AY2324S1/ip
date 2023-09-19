package duke.exceptions;

/**
 * Custom exception class for handling cases where task details are empty.
 */
public class EmptyDetailsException extends MyBotExceptions {
    /**
     * Constructs an EmptyDetailsException instance with a descriptive error message.
     *
     * @param description The description of the empty detail (e.g., "description" or "time").
     * @param taskType The type of task affected (e.g., "task" or "deadline").
     */
    public EmptyDetailsException(String description, String taskType) {
        super("☹ OOPS! The " + description + " of the " + taskType + " cannot be empty.");
    }
}

