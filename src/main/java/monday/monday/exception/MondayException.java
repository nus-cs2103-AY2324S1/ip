package monday.monday.exception;

/**
 * MondayException is a custom exception class for handling
 * exceptions related to the Monday application.
 */
public class MondayException extends Exception {

    /**
     * Constructs a MondayException object with the specified error message.
     *
     * @param message the error message associated with the exception
     */
    public MondayException(String message) {
        super(message);
    }

    /**
     * Returns a string representation of the exception message.
     *
     * @return a string representation of the exception message
     */
    @Override
    public String toString() {
        return "OOPS!!! " + super.getMessage();
    }
}
