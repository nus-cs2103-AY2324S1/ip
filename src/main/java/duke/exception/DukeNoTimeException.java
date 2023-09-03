package duke.exception;

/**
 * An Exception Class that handles incorrect time format.
 *
 * @author marioalvaro
 */
public class DukeNoTimeException extends DukeException{

    /**
     * Constructor for DukeNoTimeException.
     *
     * @param e The Exception message
     */
    public DukeNoTimeException(String e) {
        super("    ____________________________________________________________\n" +
                "     ☹ OOPS!!! Please enter the correct duke.time format.\n" +
                "    ____________________________________________________________\n");
    }
}
