package duke;

/**
 * The ToDo class represents a to-do task in the task manager.
 * It inherits from the Task class and adds functionality specific to to-do tasks.
 */
public class ToDo extends Task {

     /**
     * Constructs a ToDo object with the specified description.
     *
     * @param description The description of the to-do task.
     * @throws DukeException If the description is empty or blank.
     */
    public ToDo(String description) throws DukeException {
        super(description, TaskType.TODO);

        if (description.trim().isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    /**
     * Returns a string representation of the to-do task, including its status icon.
     *
     * @return A string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}