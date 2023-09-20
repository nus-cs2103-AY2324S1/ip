package exceptions;
/**
 * Custom exception class to handle cases where input argument has missing task description.
 */
public class InvalidTaskDescriptionException extends Exception {

    /**
     * Public constructor for throwing InvalidTaskDescriptionException
     * @param message string of text
     */
    public InvalidTaskDescriptionException(String message) {
        super("Macho! The description of a " + message + " cannot be empty, macho!");
    }
}
