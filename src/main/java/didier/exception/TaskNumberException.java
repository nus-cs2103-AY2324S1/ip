package didier.exception;

public class TaskNumberException extends DidierException {
    public TaskNumberException(String taskNumber) {
        super(taskNumber + " is an invalid task number. ");
    }
}
