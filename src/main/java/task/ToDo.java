package task;

/**
 * Represents an extension of a task.
 */
public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }
    public ToDo(String name, boolean isComplete) {
        super(name, isComplete);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String fileFormat() {
        return "TD" + DIVIDER + super.fileFormat();
    }

}
