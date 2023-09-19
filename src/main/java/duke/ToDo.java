package duke;

/**
 * Represents a ToDo task which is a subclass of Task.
 */
public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Returns a string representation of the ToDo object.
     * @return String representation of the ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the ToDo object to be saved in the file.
     * @return String representation of the ToDo object to be saved in the file.
     */
    @Override
    public String saveString() {
        return "T | " + (this.getIsDone() ? "1" : "0") + " | " + this.getTaskName();
    }
}