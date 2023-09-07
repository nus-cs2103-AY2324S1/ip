package duke.task;

/**
 * A task to be done.
 */
public class Todo extends Task {

    /**
     * Creates a todo instance.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the task.
     *
     * @return Desired string representation of the task.
     */
    @Override
    public String convertToString() {
        return "[T] " + super.convertToString();
    }

    /**
     * Returns a string representation of the task to be inserted into a file.
     *
     * @return Desired string representation of the task.
     */
    @Override
    public String convertToStringInFile() {
        return "[T] /" + super.convertToStringInFile();
    }
}
