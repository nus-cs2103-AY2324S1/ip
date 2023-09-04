package exception;

/**
 * Represents an Exception where an invalid command was given.
 */
public class InvalidCommandException extends DukeException {
    public InvalidCommandException(String command) {
        super("No such command bruh: " + command);
    }
}
