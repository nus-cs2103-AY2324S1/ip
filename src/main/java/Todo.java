public class Todo extends Task {
    // Constructor for Todo
    public Todo(String name) {
        super(name);
    }

    // Constructor for Todo with done status
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    // Gets string representation of the Todo
    public String toString() {
        return "[T]" + super.toString();
    }
}
