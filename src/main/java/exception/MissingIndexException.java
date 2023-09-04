package exception;

/**
 * Represents an Exception where an index is missing when expected.
 */
public class MissingIndexException extends DukeException {
    public MissingIndexException(String command) {
        super(command + " needs an index after it...");
    }
}
