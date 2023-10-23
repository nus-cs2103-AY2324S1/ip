package URBOI_PACKIN.TaskTypes;

import URBOI_PACKIN.Task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description, null);
    }

    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
