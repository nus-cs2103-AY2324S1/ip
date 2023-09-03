package Jeoe.Exceptions;

/**
 * This class encapsulates the class RunException.
 * It is an exception thrown when an error was encountered during the Jeoe program.
 *
 * @author Joe Chua
 * @version Week-3
 */
public class RunException extends Exception {

    /**
     * Constructor for a RunException object.
     * @param input The string input by the user to be included in the error message.
     */
    public RunException(String input) {
        super(input);
    }
}
