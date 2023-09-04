package exception;

/**
 * Represents an Exception where a command is used in the wrong context.
 */
public class WrongUseOfCommandException extends DukeException {
    public WrongUseOfCommandException() {
        super("No extra text after this command bro..");
    }
}
