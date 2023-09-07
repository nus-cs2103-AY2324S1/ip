package exceptions;
/**
 * Custom exception class to handle cases where input has invalid commands.
 */
public class InvalidCommandException extends Exception {

    /**
     * Public constructor for throwing InvalidCommandException
     * @param message string of text
     */
    public InvalidCommandException(String message) {
        super("Macho...I do not understand what " + message + " means. Please enter a correct command, macho!\n");
    }
}
