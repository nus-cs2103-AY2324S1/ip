package duke.exception;

/**
 * Represents the exception when the command is not recognised.
 * A <code>UnknownCommandException</code> object corresponds to the exception when the command is not recognised.
 */
public class UnknownCommandException extends Exception {
    /**
     * Constructs a <code>UnknownCommandException</code> object.
     */
    public UnknownCommandException() {
        super("What gibberish are you saying man?!\n");
    }
}
