package duke.exceptions;

/**
 * Custom exception class for handling cases where an input is invalid or missing a required element.
 */
public class InvalidInputException extends MyBotExceptions {
    /**
     * Constructs an InvalidInputException instance with a descriptive error message.
     *
     * @param description The description of the missing element (e.g., "description" or "time").
     * @param taskType The type of task affected (e.g., "task" or "deadline").
     */
    public InvalidInputException(String description, String taskType) {
        super("☹ OOPS! The " + description + " must have a " + taskType + ".");
    }
}
