package duke.task;

public class ToDo extends Task {
    // Constructor for duke.task.ToDo
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
