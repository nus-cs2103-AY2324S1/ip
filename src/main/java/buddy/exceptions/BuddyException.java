package buddy.exceptions;

/**
 * Represents an error that occurs while running the Buddy program.
 */
public class BuddyException extends Exception {

    /**
     * The constructor of a BuddyException.
     *
     * @param message The error message of the BuddyException.
     */
    public BuddyException(String message) {
        super(message);
    }
}
