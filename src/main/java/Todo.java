public class Todo extends Task {
    // Constructor for Todo.
    public Todo(String name) {
        super(name);
    }

    // Get string representation of the Todo.
    public String toString() {
        return "[T]" + super.toString();
    }
}
