package pooh;

/**
 * Exception thrown when an unrecognized command is entered by the user.
 * <p>
 * This exception is meant to be caught and handled by displaying an appropriate
 * error message to guide the user.
 * </p>
 */
public class UnrecognizedCommandException extends PoohException {

    /**
     * Returns a string representation of the exception, which can be used
     * to display an error message to the user.
     *
     * @return A string explaining that the command was not recognized.
     */
    @Override
    public String toString() {
        return "      ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
