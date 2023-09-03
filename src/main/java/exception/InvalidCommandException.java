package exception;

/**
 * The InvalidCommandException is thrown when the user types in an invalid input
 * command into the chatbot.
 */
public class InvalidCommandException extends DukeException {
    protected String message;

    /**
     * The constructor of InvalidCommandException.
     *
     * @param message The error message.
     */
    public InvalidCommandException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message + "☹ OOPS!!! Something went wrong D:"
                + "\n-----------------------------------------------------------------";
    }
}
