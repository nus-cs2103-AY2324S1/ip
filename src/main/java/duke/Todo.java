package duke;

import java.time.LocalDateTime;

/**
 * Represents a task of type todo
 */
public class Todo extends Task {

    /**
     * A constructor for a task of type todo.
     *
     * @param description the task details.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the task in file format.
     *
     * @return the string representation in file format.
     */
    @Override
    public String toFileFormat() {
        return "T | " + this.isDone + " | " + this.description;
    }

    /**
     * Returns the task type.
     *
     * @return the task type.
     */
    @Override
    public String getTaskType() {
        return "todo";
    }

    /**
     * Updates the task with the new description.
     *
     * @param description the new description.
     */
    @Override
    public void updateTask(String description) {
        this.description = description;
    }

    @Override
    public void updateTask(String description, LocalDateTime by) throws DukeException {
        throw new DukeException(ExceptionTypes.INCOMPLETEUPDATEDETAILS);
    }

    @Override
    public void updateTask(String description, LocalDateTime from, LocalDateTime to) throws DukeException {
        throw new DukeException(ExceptionTypes.INCOMPLETEUPDATEDETAILS);
    }
}
