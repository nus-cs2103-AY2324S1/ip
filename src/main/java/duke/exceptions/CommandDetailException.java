package duke.exceptions;
/**
 * Represents an exception specific to the chatbot.
 */
public class CommandDetailException extends ChatException {
    public CommandDetailException(String message) {
        super(message);
    }
}
