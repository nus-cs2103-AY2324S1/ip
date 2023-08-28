package teho.exceptions;

/**
 * Represents an exception that is thrown when user's command is invalid.
 */
public class InvalidCommandException extends TehOException {
    /**
     * Returns an exception message.
     *
     * @return Exception message.
     */
    @Override
    public String toString() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}

