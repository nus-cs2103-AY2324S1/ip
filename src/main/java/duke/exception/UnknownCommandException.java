package duke.exception;

/**
 * duke.exception.UnknownCommandException class is a custom duke.exception class that extends DukeException.
 * It is thrown when the user inputs an unknown command.
 * @author Alan Lim
 */
public class UnknownCommandException extends DukeException{
    /**
    * Constructor for duke.exception.UnknownCommandException.
    */
    public UnknownCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
