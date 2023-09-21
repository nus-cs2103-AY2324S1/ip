package duke;
// if the description of a todo is empty, return error message
public class EmptyTodoException extends Exception {
    public EmptyTodoException() {
        super("OOPS!!! The description of a todo cannot be empty.");
    }
}
