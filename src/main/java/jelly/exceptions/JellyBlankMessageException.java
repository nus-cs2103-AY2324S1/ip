package jelly.exceptions;

/**
 * This class handles the case where the task description is empty.
 */
public class JellyBlankMessageException extends JellyException {
    public JellyBlankMessageException(String message) {
        super("Oops! " + message + " cannot be an empty task...");
    }
}
