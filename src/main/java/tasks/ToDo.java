package tasks;

public class ToDo extends Task {

    public ToDo(String taskName) {
        super(taskName);
    }
    public ToDo(String taskName, boolean done) {
        super(taskName, done);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
