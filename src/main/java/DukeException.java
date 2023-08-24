/**
 * A custom exception class to represent the exception thrown by the Duke Chatbot.
 */
public class DukeException extends Exception {
    /**
     * Constructs a new Duke Exception with a specified error message.
     *
     * @param message The error message.
     */
    DukeException(String message) {
        super(message);
    }

}
