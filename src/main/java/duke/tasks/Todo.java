package duke.tasks;

public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), description);
    }

    @Override
    public String toFileString() {
        return String.format("T # %d # %s", (isDone ? 1 : 0), description);
    }
}
