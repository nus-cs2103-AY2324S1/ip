package task;

public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getStatusIcon() {
        return "[T]" + super.getStatusIcon();
    }

    @Override
    public String toString() {
        return String.format("T | %d | %s", super.isDone ? 1 : 0, super.description);
    }
}
