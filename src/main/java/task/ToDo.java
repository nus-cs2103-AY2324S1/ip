package task;

/**
 * Represents an extension of a task.
 */
public class ToDo extends Task {
    /**
     * Constructs a to-do.
     * @param name name of the task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Constructs a to-do with its marking status.
     * @param name name of the task.
     * @param isMarked whether the task is marked.
     */
    public ToDo(String name, boolean isMarked) {
        super(name, isMarked);
    }

    /**
     * Creates a readable string interpretation of the to-do.
     * @return a readable to-do in task form.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Produces a savable format of the to-do.
     * (We use dividers to minimize the confusion caused by spaces in names.)
     * @return a String format of to-do with minimal ambiguity in format.
     */
    @Override
    public String fileFormat() {
        return "TD" + DIVIDER + super.fileFormat();
    }

}
