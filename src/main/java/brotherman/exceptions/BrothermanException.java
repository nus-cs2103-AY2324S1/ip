package brotherman.exceptions;

/**
 * Represents an exception that occurs in Brotherman
 */
public class BrothermanException extends Exception{

    /**
     * Constructor for BrothermanException
     * @param message Message to be shown to the user
     */
    public BrothermanException(String message) {
        super(message);
    }

}
