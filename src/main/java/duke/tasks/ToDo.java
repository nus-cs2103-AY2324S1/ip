package duke.tasks;

/**
 * Represents a to-do task without any specific time constraints.
 * This class extends the duke.tasks.Task class and represents a simple to-do item.
 */
public class ToDo extends Task{
    /**
     * Constructs a duke.tasks.ToDo object with the given description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the duke.tasks.ToDo object.
     *
     * @return A formatted string including the task type and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}