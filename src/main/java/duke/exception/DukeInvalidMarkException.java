package duke.exception;

/**
 * An Exception Class that handles invalid marking index.
 *
 * @author marioalvaro
 */
public class DukeInvalidMarkException extends DukeException {

    /**
     * Constructor for DukeInvalidMarkException.
     *
     * @param e The Exception message
     */
    public DukeInvalidMarkException(String e) {
        super("    ____________________________________________________________\n" +
                "     ☹ OOPS!!! The index for marking is invalid.\n" +
                "    ____________________________________________________________\n");

    }
}
