package blip.exceptions;

/**
 * The WrongTaskNumberException class is an exception class
 * that is thrown when the index of delete/mark/unmark commands are not valid.
 */
public class WrongNumberException extends Exception{
    public WrongNumberException(String message) {
        super(message);
    }
}
