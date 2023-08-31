package tasks;

import tasks.Task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toText() {
        return "T " + this.getDoneStatus() + " " + this.description;
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
