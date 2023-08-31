package duke;

/**
 * The ToDo class represents a task with no specific deadline or timing.
 * It extends the Task class and adds functionality for managing to-do tasks.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo object with the specified task description.
     *
     * @param task The description of the to-do task.
     */
    public ToDo(String task) {
        super(task);
    }

    /**
     * Returns a string representation of the ToDo object.
     *
     * @return A string containing task type, completion status and task description.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
