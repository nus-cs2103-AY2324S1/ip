package Jelly.exceptions;

public class JellyBlankMessageException extends JellyException {
    public JellyBlankMessageException(String message) {
        super("Oops! " + message + " cannot be an empty task...");
    }
}
