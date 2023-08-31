package Duke.Exceptions;

/**
 * Represents an exception that is thrown when a command requires a description,
 * but no description is provided by the user.
 */
public class NoDescriptionException extends Exception {

    /**
     * Constructs a NoDescriptionException with a formatted error message.
     *
     * @param message The specific command that requires a description.
     */
    public NoDescriptionException(String message) {
        super(String.format("☹ OOPS!!! Where is your description for %s?", message));
    }

}
