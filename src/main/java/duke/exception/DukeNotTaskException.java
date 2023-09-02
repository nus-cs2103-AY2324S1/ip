package duke.exception;

/**
 * An Exception Class that handles unknown command.
 *
 * @author marioalvaro
 */
public class DukeNotTaskException extends DukeException{

    /**
     * Constructor for DukeNotTaskException.
     *
     * @param e The Exception message
     */
    public DukeNotTaskException(String e) {
        super("    ____________________________________________________________\n" +
                "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                "    ____________________________________________________________\n");
    }
}
