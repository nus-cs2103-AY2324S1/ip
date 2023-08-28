package duke.exception;

public class EmptyDescriptionException extends DukeException {

    public EmptyDescriptionException(String taskType) {
        super(generateErrorMessage(taskType));
    }

    private static String generateErrorMessage(String taskType) {
        if (taskType.equals("event")) {
            return " ☹ OOPS!!! The description of an event cannot be empty.";
        } else {
            return " ☹ OOPS!!! The description of a " + taskType + " cannot be empty.";
        }
    }
}
