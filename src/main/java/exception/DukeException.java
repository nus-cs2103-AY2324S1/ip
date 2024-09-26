package exception;

/**
 * A custom exception class to represent the exception thrown by the duke.Duke Chatbot.
 */
public class DukeException extends Exception {
    /**
     * Constructs a new duke.Duke Exception with a specified error message.
     *
     * @param message The error message.
     */
    DukeException(String message) {
        super(message);
    }

}
