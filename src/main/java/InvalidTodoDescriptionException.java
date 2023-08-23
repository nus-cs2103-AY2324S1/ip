public class InvalidTodoDescriptionException extends Exception {
    public InvalidTodoDescriptionException() {
        super("OOPS!!! The description of a Todo cannot be empty.");
    }
}
