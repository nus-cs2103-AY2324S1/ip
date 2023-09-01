public class Todo extends Task {
    public Todo(String description) {
        super("TODO", description);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
