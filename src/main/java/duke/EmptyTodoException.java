package duke;

public class EmptyTodoException extends Exception {
    // Gets string representation of exception
    public String toString() {
        return "Description of duke.Todo cannot be empty.";
    }
}
