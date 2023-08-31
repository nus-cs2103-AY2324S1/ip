package Exceptions;
/**
 * Custom exception class to handle cases where input has invalid arguments.
 */
public class InvalidArgumentException extends Exception{
    public InvalidArgumentException(String message, String cmd, String divider) {
        super("Wrong argument '" + message + "' provided, macho! Please use " + cmd
                + " for any deadline tasks, macho!\n" + divider);
    }
}
