package duke.exception;

/**
 * An Exception Class that handles wrong index in Delete.
 *
 * @author marioalvaro
 */
public class DukeInvalidDeleteException extends DukeException {

    /**
     * Constructor for DukeInvalidDeleteException.
     *
     * @param e The Exception message
     */
    public DukeInvalidDeleteException(String e) {
        super("     ☹ OOPS!!! The index for deleting is invalid.\n");

    }
}
