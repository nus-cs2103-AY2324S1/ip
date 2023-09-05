package seedu.duke.exceptions;

/**
 * InvalidCommandException exception
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        super("InvalidCommandException: OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }

    @Override
    public String getMessage() {
        return "I'm sorry, but I don't know what that means :-(";
    }
}
