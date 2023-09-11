package exception;

/**
 * Exception for the event where user input has missing arguments.
 */
public class MissingArgumentException extends Exception {

    /**
     * Returns a string to inform users of the exception
     * @return string information to inform user of exception
     */
    @Override
    public String toString() {
        return "There are missing arguments for the command you just entered.";
    }
}
