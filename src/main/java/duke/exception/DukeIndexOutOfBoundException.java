package duke.exception;

/**
 * An Exception Class that handles Index out of Bound.
 *
 * @author marioalvaro
 */
public class DukeIndexOutOfBoundException extends DukeException{

    /**
     * Constructor for DukeIndexOutOfBoundException.
     *
     * @param e The Exception message
     */
    public DukeIndexOutOfBoundException(String e) {
        super("    ____________________________________________________________\n" +
                "     ☹ OOPS!!! The description cannot be empty.\n" +
                "    ____________________________________________________________\n");
    }
}
