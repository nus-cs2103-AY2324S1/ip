public class Todo extends Task {
    public Todo(String description) {
        super(description, false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
