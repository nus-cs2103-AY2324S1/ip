package exceptions;

/**
 * Represents the exception thrown when the user inputs a task without invalid parameters.
 */
public class InvalidParametersException extends Exception{
    public InvalidParametersException(String parameter, String usage) {
        super("OOPS!!! The " + parameter + " parameter is invalid!\n    Usage: " + usage);
    }
}
