package thea;

/**
 * Represents a ToDo task.
 * This class is a subclass of the abstract class Task.
 * This class is the simplest task subclass with just a description.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo object.
     *
     * @param taskName description of the task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Returns the task in memory format, which means the format
     * of which the task is saved to the hard disk.
     *
     * @return task in memory format.
     */
    @Override
    public String toMemoryFormat() {
        return String.format("T | %s | %s", (super.isDone ? "1" : "0"), super.taskName);
    }

    /**
     * Returns the task in desired string format.
     *
     * @return task in string format.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", (super.isDone ? "X" : " "), super.taskName);
    }
}
