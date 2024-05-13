package duke.exception;

/**
 * Represents an exception that occurs when the arguments of a command are invalid.
 */
public class DukeIllegalArgumentsException extends DukeException {
    /**
     * Constructs a DukeIllegalArgumentsException with the specified detail message.
     *
     * @param message The detail message.
     */
    public DukeIllegalArgumentsException(String message) {
        super(message);
    }
}
