package smolbrain.exception;

/**
 * Handles an invalid or unknown command given by user.
 */
public class InvalidCommandException extends Exception{

    /**
     * Creates the exception.
     */
    public InvalidCommandException() {
        super();
    }

    /**
     * Returns the string representation of the exception.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "I'm sorry, but I don't know what that means :-(";
    }

}