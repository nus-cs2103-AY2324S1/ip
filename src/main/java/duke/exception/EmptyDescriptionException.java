package duke.exception;

/**
 * Represents an exception that occurs when the description of a task is empty.
 */
public class EmptyDescriptionException extends DukeException {

    /**
     * Constructs an EmptyDescriptionException with an error message based on the task type.
     *
     * @param taskType The type of task for which the description is empty or missing.
     */
    public EmptyDescriptionException(String taskType) {
        super(generateErrorMessage(taskType));
    }

    /**
     * Generates an error message based on the task type.
     *
     * @param taskType The type of task for which the description is empty.
     * @return An error message.
     */
    private static String generateErrorMessage(String taskType) {
        if (taskType.equals("event")) {
            return " ☹ OOPS!!! The description of an event cannot be empty.";
        } else {
            return " ☹ OOPS!!! The description of a " + taskType + " cannot be empty.";
        }
    }
}
