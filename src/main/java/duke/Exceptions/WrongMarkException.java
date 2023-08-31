package duke.Exceptions;

/**
 * Subclass of the DukeException class
 */
public class WrongMarkException extends DukeException {
    /**
     * Constructor of the WrongMarkException class, used to deal with
     * errors when command is given to mark a marked task and unmark a task
     * that is not marked yet
     * @param message is the message given when you mark a task that is not
     *                marked yet
     */
    public WrongMarkException(String message) {
        super(message);
    }
}
