package Duke.Exceptions;

/**
 * Contains the main Custom Exception for DukeBot.
 */
public class DukeException extends Exception {

    /**
     * Constructor for the Exception.
     *
     * @param message the error message
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns the error message for this exception.
     *
     * @return
     */
    public String getErrorMessage() {
        return getMessage();
    }


}
