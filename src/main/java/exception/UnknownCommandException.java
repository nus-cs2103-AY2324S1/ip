package exception;

/**
 * exception.UnknownCommandException class is a custom exception class that extends DukeException.
 * It is thrown when the user inputs an unknown command.
 * @author Alan Lim
 */
public class UnknownCommandException extends DukeException{
    /**
    * Constructor for exception.UnknownCommandException.
    */
    public UnknownCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
