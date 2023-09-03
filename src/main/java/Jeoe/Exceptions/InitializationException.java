package Jeoe.Exceptions;

/**
 * This class encapsulates the class InitializationException.
 * It is an exception thrown when initialization of the Jeoe program fails.
 *
 * @author Joe Chua
 * @version Week-3
 */
public class InitializationException extends Exception {

    /**
     * Constructor for a InitializationException object.
     * @param input The string input by the user to be included in the error message.
     */
    public InitializationException(String input) {
        super("Initialization exception thrown");
    }
}
