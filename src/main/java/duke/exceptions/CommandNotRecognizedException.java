package duke.exceptions;

/**
 * Represents an exception specific to the chatbot.
 */
public class CommandNotRecognizedException extends ChatException {
    public CommandNotRecognizedException(String message) {
        super(message);
    }
}
