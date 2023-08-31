public class Todo extends Task {
    public Todo(String description) throws DukeException {
        super(description);
    }

    public Todo(String description, boolean isDone) throws DukeException {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
