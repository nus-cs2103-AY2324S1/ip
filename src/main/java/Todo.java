public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, int isMarked) {
        super(description, isMarked);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
