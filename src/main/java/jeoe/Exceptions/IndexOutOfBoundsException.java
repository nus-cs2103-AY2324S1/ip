package jeoe.Exceptions;

/**
 * This class encapsulates the class InitializationException.
 * It is an exception thrown when initialization of the Jeoe program fails.
 *
 * @author Joe Chua
 * @version Week-6
 */
public class IndexOutOfBoundsException extends Exception {

    /**
     * Constructor for a IndexOutOfBoundsException object.
     * @param input The string input by the user to be included in the error message.
     */
    public IndexOutOfBoundsException(String input) {
        super("Index Out Of Bounds exception thrown, index " + input + " is out of bounds");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
