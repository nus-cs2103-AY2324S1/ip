package duke.task;
import java.time.LocalDateTime;

import duke.InvalidTaskCreationException;


/**
 * The `ToDo` class represents a simple task without a specific deadline or event time range in the Duke application.
 * It is a subclass of the `Task` class.
 */
public class ToDo extends Task {

    /**
     * Constructs a new `ToDo` task with the specified description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Parses user input and creates a `ToDo` task.
     *
     * @param description The description of the `ToDo` task.
     * @return A `ToDo` task instance.
     * @throws InvalidTaskCreationException if the description is empty.
     */
    public static ToDo toDoCon(String description) throws InvalidTaskCreationException {
        if (description.equalsIgnoreCase("")) {
            throw new InvalidTaskCreationException("OOPS!!! The description of a todo cannot be empty.");
        }
        return new ToDo(description);
    }

    /**
     * Gets the urgency date and time for the `ToDo` task, which is the current date and time.
     *
     * @return The urgency date and time (current date and time).
     */
    public LocalDateTime getUrgencyDate() {
        return LocalDateTime.now();
    }

    /**
     * Converts the `ToDo` task to a formatted string representation for display.
     *
     * @return A formatted string representing the `ToDo` task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
