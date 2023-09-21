package jarvis.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description, false);
    }

    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toSaveString() {
        return "T | " + super.toSaveString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
