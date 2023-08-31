package helpbuddy.exception;

/**
 * Thrown when HelpBuddy chatbot detects invalid input by user.
 */
public class HelpBuddyException extends Exception {
    /**
     * Reports a HelpBuddyException for the message specified.
     * @param message String describing the reason for exception.
     */
    public HelpBuddyException(String message) {
        super(message);
    }

    /**
     * Produces the error message.
     * @return the error message along with "OOPS!".
     */
    @Override
    public String getMessage() {
        return "OOPS! " + super.getMessage();
    }
}
