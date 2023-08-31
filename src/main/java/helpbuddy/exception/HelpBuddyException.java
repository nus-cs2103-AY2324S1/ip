package helpbuddy.exception;

/**
 * A class that handles errors for invalid inputs to HelpBuddy.
 */
public class HelpBuddyException extends Exception {
    /**
     * A constructor for the HelpBuddyException.
     * @param message Error message to be displayed to users.
     */
    public HelpBuddyException(String message) {
        super(message);
    }

    /**
     * @return The error message.
     */
    @Override
    public String getMessage() {
        return "OOPS! " + super.getMessage();
    }
}
