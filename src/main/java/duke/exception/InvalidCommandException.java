package duke.exception;

/**
 * Exception thrown when a command is not recognised.
 */
public class InvalidCommandException extends DukeException {

    /**
     * Constructs InvalidCommandException.
     *
     * @param cmd The invalid command entered.
     */
    public InvalidCommandException(String cmd) {
        super(String.format("☹ OOPS!!! I'm sorry, but I don't know what \"%s\" means :-(", cmd));
    }

}
