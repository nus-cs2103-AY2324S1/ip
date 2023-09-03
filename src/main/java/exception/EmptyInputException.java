package exception;

/**
 * EmptyInputException is thrown when the user types in an empty input into the chatbot.
 */
public class EmptyInputException extends DukeException {
    protected String message;

    /**
     * The constructor of EmptyInputException.
     *
     * @param message The error message.
     */
    public EmptyInputException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! The description of a " + this.message + " cannot be empty."
                + "\n-----------------------------------------------------------------";
    }
}
