package duke.exceptions;

/**
 * Exception class that indicates the command is typed wrongly
 */
public class InvalidCommandSyntaxException extends DukeException {
    public InvalidCommandSyntaxException(String errorMessage) {
        super(errorMessage);
    }
}
