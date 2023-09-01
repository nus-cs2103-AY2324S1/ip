package duke.exception;

/**
 * Represents the exception when the description of the command is empty.
 * A <code>EmptyDescriptionException</code> object corresponds to the exception when the description of the command is empty.
 */
public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException() {
        /**
         * Constructs a <code>EmptyDescriptionException</code> object.
         */
        super("Type something in the description you dumb dumb, uwu.. ><\n");
    }
}
