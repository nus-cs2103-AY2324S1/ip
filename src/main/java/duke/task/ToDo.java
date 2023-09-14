package duke.task;

/**
 * Represents a todo task.
 * Inherits from the Task class.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo object with the given description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Checks if another task is a duplicate of this todo task.
     *
     * @param otherTask The other task to compare with.
     * @return true if the tasks are duplicates, false otherwise.
     */
    @Override
    public boolean isDuplicate(Task otherTask) {
        if (otherTask instanceof ToDo) {
            ToDo otherToDo = (ToDo) otherTask;
            return this.getDescription().equals(otherToDo.getDescription());
        }
        return false;
    }

    /**
     * Returns a string representation of the ToDo object.
     *
     * @return A string representation of the ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a formatted string representation of the ToDo object.
     *
     * @return A formatted string representation of the ToDo object.
     */
    @Override
    public String toFormattedString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
