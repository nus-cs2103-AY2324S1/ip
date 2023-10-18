package duke.exceptions;

/**
 * Exception when no description is found.
 */
public class EmptyDescription extends DukeException {
    /**
     * Constructor for the EmptyDescription class.
     */
    public EmptyDescription() {
        super("-------------------------------\n"
                + "OOPS!!! The description of a todo cannot be empty.\n"
                + "-------------------------------\n");
    }
}

