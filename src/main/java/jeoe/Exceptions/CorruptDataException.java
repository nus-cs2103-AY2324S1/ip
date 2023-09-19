package jeoe.Exceptions;

/**
 * This class encapsulates the class InitializationException.
 * It is an exception thrown when initialization of the Jeoe program fails.
 *
 * @author Joe Chua
 * @version Week-6
 */
public class CorruptDataException extends Exception {

    /**
     * Constructor for a CorruptDataException object.
     * @param input The string input by the user to be included in the error message.
     */
    public CorruptDataException(String input) {
        super("Corrupt data exception thrown");
    }
}
