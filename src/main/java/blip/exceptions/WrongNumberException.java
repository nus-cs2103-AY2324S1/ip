package blip.exceptions;

/**
 * The WrongNumberException class is an exception class
 * that is thrown when the index of delete/mark/unmark/priority commands are not valid.
 */
public class WrongNumberException extends Exception{
    /**
     * Creates an instance of WrongNumberException
     *
     * @param message
     */
    public WrongNumberException(String message) {
        super(message);
    }
}
