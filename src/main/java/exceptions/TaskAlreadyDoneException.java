package exceptions;

public class TaskAlreadyDoneException extends Exception {
    public TaskAlreadyDoneException(String message) {
        super(message);
    }
}