package exceptions;

public class TaskException extends RuntimeException {
    public TaskException() {
        super("Invalid task provided.");
    }
}
