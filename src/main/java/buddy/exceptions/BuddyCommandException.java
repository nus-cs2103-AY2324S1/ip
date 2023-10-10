package buddy.exceptions;

/**
 * Represents an error which occurs while executing a Command.
 */
public class BuddyCommandException extends Exception {
    /**
     * The constructor of a BuddyCommandException.
     *
     * @param message The error message of the BuddyCommandException.
     */
    public BuddyCommandException(String message) {
        super(String.format("Whoops! Try this instead: \n%s", message));
    }
}
