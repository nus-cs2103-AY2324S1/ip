package brandon.chatbot.common;

/**
 * Represents any exception dealt with duke.
 */
public class DukeException extends Exception {
    private String message;

    /**
     * Constructs an instance of DukeException with a message passed as a parameter
     * @param message of the DukeException to be printed as a feedback.
     */
    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
