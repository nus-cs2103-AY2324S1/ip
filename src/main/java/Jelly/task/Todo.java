package Jelly.task;

/**
 * Corresponds to a task without any time specified.
 */
public class Todo extends Task {

    /**
     * Constructor for a to do task.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * String representation of a to do task.
     *
     * @return A string with label [T] for to do, and the toString of Task.
     */
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String writeToFile() {
        return "T | " + (getIsDone() ? "1" : "0") + " | " + getDescription();
    }
}
