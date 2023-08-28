package task;

import task.Task;

public class ToDo extends Task {
    public ToDo(String nameOfTask) {
        super(nameOfTask);
    }

    @Override
    public String toString() {
        if (completed) {
            return "[T][X] " + nameOfTask;
        } else {
            return "[T][ ] " + nameOfTask;
        }
    }
}
