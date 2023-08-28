package duke.exception;

public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String taskType, String format) {
        super(String.format("☹ OOPS!!! The description of a %s cannot be empty.\nExample: %s", taskType, format));
    }
}
