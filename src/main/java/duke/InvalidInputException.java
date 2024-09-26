package duke;

/**
 * InvalidInputException class that is thrown whenever there is InvalidInput
 */
public class InvalidInputException extends Exception {
    /**
     * Constructor for InvalidInputException
     * @param message String message which we want to convey to user
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
