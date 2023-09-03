package duke.task;

import duke.task.Task;

/**
 * Represents a todo task that has a description.
 */
public class ToDo extends Task {
    /**
     * Constructor with description.
     *
     * @param description The description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the duke.task.ToDo object.
     *
     * @return A string representation of the duke.task.ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
