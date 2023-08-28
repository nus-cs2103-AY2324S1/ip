package exception;

/**
 * Checked Exception for the event where the input time is not a valid time.
 */
public class InvalidTimeException extends Exception{

    /**
     * Returns a string to inform users that the time is invalid
     * @return string information to inform user of exception
     */
    @Override
    public String toString() {
        return "The given date is invalid. Returning to homepage...";
    }
}
