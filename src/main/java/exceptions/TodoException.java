package exceptions;

public class TodoException extends RuntimeException {
    public TodoException() {
        super("Invalid format for Todo task. Please adhere to the following:\n" +
                "todo (task)");
    }
}
