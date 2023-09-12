package duke.dukeexceptions;

/**
 * An exception thrown when an invalid command is entered
 */
public class InvalidCommandException extends Exception {
    private final String message;

    /**
     * Constructs an InvalidCommandException with an empty message
     */
    public InvalidCommandException() {
        super();
        this.message = "";
    }

    /**
     * Constructs an InvalidCommandException with the given message
     *
     * @param message the message to be displayed
     */
    public InvalidCommandException(String message) {
        super();
        this.message = message;
    }

    /**
     * Returns the message associated with the exception to be printed to the terminal
     *
     * @return the message associated with the exception to be printed to the terminal
     */
    public String getBotMessage() {
        return this.message;
    }
}
