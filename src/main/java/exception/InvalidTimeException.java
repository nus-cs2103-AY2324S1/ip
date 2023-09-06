package exception;

/**
 * Checked Exception for the event where the input time is not a valid time.
 */
public class InvalidTimeException extends Exception {

    /**
     * Returns a string to inform users that the time is invalid
     * @return string information to inform user of exception
     */
    @Override
    public String toString() {
        return "The given time is invalid. "
                + "Please type in a valid time in the range between 0000 - 2359";
    }
}
