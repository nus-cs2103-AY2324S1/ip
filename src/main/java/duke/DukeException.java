package duke;

/**
 * Custom exception class that handles malformed inputs unique to the chatbot.
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String getMessage() {
        return "⚠ Eek! " + super.getMessage();
    }
}
