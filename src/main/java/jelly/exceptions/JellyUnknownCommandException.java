package jelly.exceptions;

/**
 * Handles the case where the user inputs a command that is unsupported by Jelly chatbot.
 */
public class JellyUnknownCommandException extends JellyException {
    public JellyUnknownCommandException() {
        super("Oops! I really don't know what you're saying :(");
    }
}
