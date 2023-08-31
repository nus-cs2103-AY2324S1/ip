package tasks;

import tasks.Task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
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
