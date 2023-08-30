package exceptions;

public class DeadlineException extends RuntimeException{
    public DeadlineException() {
        super("Invalid format for Deadline task. Please adhere to the following:\n" +
                "deadline (task) /by (deadline)");
    }
}
