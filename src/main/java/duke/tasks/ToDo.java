package duke.tasks;

/**
 * Represents a ToDo task object.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo object with given description.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Updates description of task
     *
     * @param newDescription New description.
     */
    @Override
    public void updateTaskDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Represents ToDo task in string format.
     *
     * @return String representation of ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Represents ToDo task in string format to be stored.
     *
     * @return String representation of ToDo task.
     */
    @Override
    public String toStorageFormat() {
        return ("T" + super.toStorageFormat());
    }
}
