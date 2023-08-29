package duke.task;

import duke.task.Task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String status = "[" + (super.isDone ? "X" : " ") + "]";
        return "[T]" + status + " " + super.description;
    }
}
