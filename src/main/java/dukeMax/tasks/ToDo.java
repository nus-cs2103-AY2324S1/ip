package tasks;

public class ToDo extends Task {
    public ToDo(String task) {
        super(task);
    }

    @Override
    public String getStatus() {
        return "[To-Do]" + super.getStatus();
    }

    @Override
    public String toFile() {
        return super.isDone ? ("T | 1 | " + super.task) : ("T | 0 | " + super.task);
    }
}