package duke.task;

/**
 * Represents a ToDo inherits from a Task.
 */
public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String exportToText() {
        return String.format("todo,>%s", super.exportToText());
    }
}
