package didier.exception;

/**
 * Thrown to indicate that the task number index on which the user tried to do an operation is invalid.
 */
public class TaskNumberException extends DidierException {
    public TaskNumberException(String taskNumber) {
        super(taskNumber + " is an invalid task number. ");
    }
}
