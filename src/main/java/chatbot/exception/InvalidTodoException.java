package chatbot.exception;

public class InvalidTodoException extends Exception {
    public InvalidTodoException() {
        super("OOPS!!! Please specify the description for this Todo!");
    }
}
