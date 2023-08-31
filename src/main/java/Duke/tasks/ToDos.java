package Duke.tasks;
import core.DukeException;

/**
 * Represents a ToDo task without a specific deadline or event date.
 * A ToDo task is represented by a description.
 */
public class ToDos extends Task {

    /**
     * Creates a new ToDo task with the given description.
     *
     * @param description The description of ToDo task.
     * @throws DukeException If the description is null or empty.
     */
    public ToDos(String description) throws DukeException {
        super(description);
        if (description == null || description.trim().isEmpty()) {
            throw new DukeException("OOPS! The description of ToDo cannot be empty!");
        }
    }

    public ToDos(String description, boolean isCompleted) throws DukeException {
        super(description);
        if (description == null || description.trim().isEmpty()) {
            throw new DukeException("OOPS! The description of ToDo cannot be empty!");
        }
        this.isCompleted = isCompleted;
    }

    /**
     * Indicates that this task is of type "T".
     *
     * @return The string "T" representing a ToDo task.
     */
    @Override
    public String getType() {
        return "T";
    }
}
