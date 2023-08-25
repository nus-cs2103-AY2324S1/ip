package exceptions;

public class TaskException extends Exception {
    public TaskException() {
        super("Invalid task provided.");
    }
}
