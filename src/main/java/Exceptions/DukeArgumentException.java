package Exceptions;
/**
 * This class represents the exception which is thrown when there is an incorrect argument
 */
public class DukeArgumentException extends Exception{
    public DukeArgumentException (String message) {
        super(message);
    }
}
