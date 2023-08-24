package exceptions;

public class TaskNotDoneException extends Exception {
    public TaskNotDoneException(String message) {
        super(message);
    }
}