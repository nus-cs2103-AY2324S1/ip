package chatbot.exception;

public class InvalidEventException extends Exception {
    public InvalidEventException() {
        super("OOPS!!! Please specify the description, start, and end time in the correct format for this Event!");
    }
}
