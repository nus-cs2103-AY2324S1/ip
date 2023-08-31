package seedu.duke;

/**
 * Encapsulates the ToDo class.
 * A ToDo is a Task with a ToDo type.
 */
public class ToDo extends Task {
    protected String type = "T";

    /**
     * Creates a ToDo instance.
     *
     * @param name The name of the task given by the user.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Obtains the task type in square brackets.
     *
     * @return A string containing the task type in square brackets.
     */
    @Override
    public String getTaskType() {
        return "[" + this.type + "]";
    }
}
