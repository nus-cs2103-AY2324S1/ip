package duke.exception;

public class EmptyDescriptionException extends MissingInformationException {
    public EmptyDescriptionException(String taskType) {
        super("☹ OOPS!!! The description of a " + taskType + " cannot be empty.");
    }
}
