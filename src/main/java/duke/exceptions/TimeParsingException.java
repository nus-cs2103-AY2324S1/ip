package duke.exceptions;
/**
 * Represents an exception specific to the chatbot.
 */
public class TimeParsingException extends ChatException {
    public TimeParsingException(String message) {
        super("I'm sorry, I don't understand the Duke.time you entered: " + message + "\n");
    }
}
