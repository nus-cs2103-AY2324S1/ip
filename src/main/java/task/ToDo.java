package task;

/**
 * Represents an extension of a task.
 */
public class ToDo extends Task {
    /**
     * This is the constructor for a deadline.
     * @param name name of the task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * An overloading of the above method, for when the isComplete is needed as well.
     * @param name name of the task.
     * @param isComplete whether the task is complete.
     */
    public ToDo(String name, boolean isComplete) {
        super(name, isComplete);
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
