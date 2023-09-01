package smolbrain.exception;

/**
 * Handles an invalid date or time given by user.
 */
public class InvalidDateTimeException extends Exception {

    /**
     * Creates the exception.
     */
    public InvalidDateTimeException() {
        super();
    }

    /**
     * Returns the string representation of the exception.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "Please provide a valid date and time with format of dd//MM/yyyy HHmm.";
    }

}
