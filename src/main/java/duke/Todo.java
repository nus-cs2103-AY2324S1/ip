package duke;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    @Override
    public String toDataString() {
        return "TODO | " + super.toDataString();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
