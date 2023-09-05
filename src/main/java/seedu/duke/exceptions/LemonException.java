package seedu.duke.exceptions;

/**
 * The LemonException wraps all exceptions thrown by the commands from the users' input.
 */
public class LemonException extends Exception {
    public LemonException(String message) {
        super(message);
    }
}

