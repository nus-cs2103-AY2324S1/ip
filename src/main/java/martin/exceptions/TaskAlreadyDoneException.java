package martin.exceptions;

public class TaskAlreadyDoneException extends MartinException {
    public TaskAlreadyDoneException(String message) {
        super(message);
    }
}