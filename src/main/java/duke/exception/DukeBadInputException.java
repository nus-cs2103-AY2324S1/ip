package duke.exception;

/**
 * Signals when a user input is unable to be parsed or out of bound
 */
public class DukeBadInputException extends Exception {
    /**
     * Constructs an BadInputException with the specified detail message.
     *
     * @param e - description of the bad input
     */
    public DukeBadInputException(String e) {
        super(e);
    }
}
