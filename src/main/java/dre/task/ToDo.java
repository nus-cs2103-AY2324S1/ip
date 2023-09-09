package dre.task;

/**
 * Represents a simple to-do task.
 */
public class ToDo extends Task {

    /**
     * Creates a new to-do task.
     *
     * @param task The description of the task.
     */
    public ToDo(String task) {
        super(task);
    }

    /**
     * Provides a formatted string for saving this to-do task to a file.
     *
     * @return A formatted string suitable for file storage.
     */
    @Override
    public String fileSaveFormat() {
        return "T|" + super.fileSaveFormat();
    }

    /**
     * Converts the task to a string format for display.
     *
     * @return A formatted string representing this task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
