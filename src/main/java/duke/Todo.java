package duke;

/**
 * Represents a <code>Todo</code> object that extends from <code>Task</code>.
 * A <code>Todo</code> object contains a description.
 */
public class Todo extends Task {

    /**
     * Constructs a new <code>Todo</code> object.
     * @param description the description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Gets the description of the <code>Todo</code> object.
     * @return String representation of the <code>Todo</code> object to be displayed.
     */
    @Override
    public String getDescription() {
        return "[T] " + super.getDescription();
    }

    /**
     * Gets the description of the <code>Todo</code> object.
     * @return String representation of the <code>Todo</code> object to save to file.
     */
    @Override
    public String getSavedString() {
        return "T " + super.getSavedString();
    }

}
