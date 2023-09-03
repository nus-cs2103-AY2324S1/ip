package exception;

/**
 * InvalidFormatException is thrown when user inputs and invalid format
 * for the user commands of deadline and event.
 */
public class InvalidFormatException extends DukeException {
    protected String message;

    protected String subMessage;

    /**
     * The constructor of InvalidFormatException.
     *
     * @param message The error message.
     */
    public InvalidFormatException(String message, String subMessage) {
        this.message = message;
        this.subMessage = subMessage;
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! The format of the " + this.message + " is incorrect :(((\n"
                + "Please add a \"" + this.subMessage + "\" keyword in"
                + "\n-----------------------------------------------------------------";
    }
}
