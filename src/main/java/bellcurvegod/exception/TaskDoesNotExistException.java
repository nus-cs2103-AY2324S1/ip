package bellcurvegod.exception;

/**
 * Encapsulates an exception thrown when there is no task with given index in the list.
 */
public class TaskDoesNotExistException extends BellCurveGodException {
    public TaskDoesNotExistException(String message) {
        super(message);
    }
}
