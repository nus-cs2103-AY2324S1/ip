package duke.taskClasses;

/**
 * Represents a ToDo task.
 * ToDo tasks are a type of task with only a description and no additional time-related details.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the given description.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description, "T");
        this.addedTaskDescription();
    }

    /**
     * Provides additional details for the ToDo task.
     * Since ToDo tasks have no extra details, this returns an empty string.
     *
     * @return An empty string.
     */
    @Override
    public String getDetails() {
        return "";
    }

    /**
     * Provides a formatted string for storing the ToDo task in a database.
     *
     * @return A formatted string representing the ToDo task for storage.
     */
    @Override
    public String getDBString() {
        return String.format("%s | %s | %s",
                "T", this.isDone() ? "1" : "0",
                this.description);
    }
}
