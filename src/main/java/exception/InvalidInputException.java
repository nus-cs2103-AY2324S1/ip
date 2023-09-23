package exception;

/**
 * Checked Exception for the event where the input is not valid.
 */
public class InvalidInputException extends Exception {

    /**
     * Returns a string to inform users that the input is invalid
     * @return string information to inform user of exception
     */
    @Override
    public String toString() {
        return "Your given input is invalid. Please type an appropriate input.";
    }
}
