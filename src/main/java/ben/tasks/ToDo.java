package ben.tasks;

import ben.tasks.Task;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {

    /**
     * Takes in a description and whether the Todo is completed.
     *
     * @param description Description of ToDo.
     * @param isCompleted Whether the task is completed.
     */
    public ToDo(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    /**
     * Represents the ToDo.
     *
     * @return String representation of ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Represents the ToDo when it is saved to the file.
     *
     * @return String representation of ToDo.
     */
    @Override
    public String saveString() {
        return "T|" + super.saveString();
    }
}
