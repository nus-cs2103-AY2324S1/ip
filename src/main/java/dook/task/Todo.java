package dook.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);

    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);

    }

    @Override
    public String getSaveableString() {
        return String.format("T//%s//%s", getStatusIcon(), description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
