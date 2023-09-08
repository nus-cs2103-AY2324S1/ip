package duke;

/**
 * Custom exception class for Duke chatbot.
 * This exception throws when issue or error.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
    @Override
    public String toString() {
        return super.getMessage();
    }
}
