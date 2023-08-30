package exception;
/**
 * The Exception for invalid event command
 */
public class InvalidEventException extends Exception{
    // Constructor

    /**
     * The constructor of InvalidEventException
     */
    public InvalidEventException() {
        super("☹ OOPS!!! I'm sorry, but the input Event is invalid :-(");
    }
}
