package duke.exception;

/**
 * An Exception Class that handles specifically to Duke.
 *
 * @author marioalvaro
 */
public class DukeException extends Exception{

    /**
     * Constructor for DukeException.
     *
     * @param e The Exception message
     */
    public DukeException(String e) {
        super(e);
    }
}
