package duke;
public class EmptyTodoException extends Exception {
    public EmptyTodoException() {
        super("OOPS!!! The description of a todo cannot be empty.");
    }
}
