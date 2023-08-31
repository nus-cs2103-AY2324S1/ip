package monday.monday.exception;

/**
 * MondayExceptions is a custom exception class for handling
 * exceptions related to the Monday application.
 */
public class MondayExceptions extends Exception {

    /**
     * Constructs a MondayExceptions object with the specified error message.
     *
     * @param message the error message associated with the exception
     */
    public MondayExceptions(String message) {
        super(message);
    }

    /**
     * Returns a string representation of the exception message.
     *
     * @return a string representation of the exception message
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! " + super.getMessage();
    }
}