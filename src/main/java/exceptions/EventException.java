package exceptions;

public class EventException extends RuntimeException {
    public EventException() {
        super("Invalid format for Event task. Please adhere to the following:\n" +
                "event (task) /from (start) /to (end)");
    }
}
