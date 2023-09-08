package duke.task;

/**
 * The ToDo class represents a task without a specific deadline.
 * It is a subclass of the Task class.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with a description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a formatted string representation of the ToDo task for saving to a file.
     *
     * @return A string in the format "[T] | [X] | Description".
     */
    @Override
    public String toTxtString() {
        return "[T] | [" + (this.isDone ? "X" : " ") + "] | " + this.description;
    }

    /**
     * Returns a string representation of the ToDo task for displaying to the user.
     *
     * @return A string in the format "[T] [Status] Description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
