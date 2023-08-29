package duke;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toLogString() {
        return String.format("T|%s|%s", (isDone ? "X" : "O"), description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
