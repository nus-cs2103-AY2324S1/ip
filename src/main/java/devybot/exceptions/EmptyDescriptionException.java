package devybot.exceptions;

/**
 * Signals that an attempt to create a task with an empty description has
 * occurred in the DevyBot application.
 */
public class EmptyDescriptionException extends DevyBotException {

    /**
     * Constructs a new EmptyDescriptionException with a specific task type.
     *
     * @param taskType the type of task for which the description is empty
     */
    public EmptyDescriptionException(String taskType) {
        super("☹ OOPS!!! The description of a " + taskType + " cannot be empty.");
    }
}