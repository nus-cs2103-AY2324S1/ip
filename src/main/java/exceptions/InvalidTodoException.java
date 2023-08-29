package exceptions;

public class InvalidTodoException extends DukeException {
    public InvalidTodoException() {
        super("Missing description for todo task.\n"
                + "Format should be: todo <task description>");
    }
}
