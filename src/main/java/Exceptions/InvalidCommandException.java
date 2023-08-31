package Exceptions;
/**
 * Custom exception class to handle cases where input has invalid commands.
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String message) {
        super("Macho...I do not understand what this means. Please enter a correct command, macho!\n"
        + message);
    }
}
