/**
 * Represents exception when there are insufficient arguments when creating a
 * task.
 */
public class InsufficientArgumentsException extends DukeException {
    public InsufficientArgumentsException(String message) {
        super(message);
    }
}
