package exception;

/**
 * Checked Exception for the event where the input date is not a valid date.
 */
public class InvalidDateException extends Exception {

    /**
     * Returns a string to inform users that the date is invalid
     * @return string information to inform user of exception
     */
    @Override
    public String toString() {
        return "The given date is invalid. Please type in a valid date.";
    }
}
