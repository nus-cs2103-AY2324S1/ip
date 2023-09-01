package duke.Exceptions;

public class InvalidTodoException extends LemonException {
    public InvalidTodoException(String message) {
        super(":( OPPS!!! The description of a todo cannot be empty.");
    }
}
