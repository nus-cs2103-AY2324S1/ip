package duke.tasks;

import duke.components.Status;

/**
 * Encapsulates a ToDo. Contains the task description, and
 * completion status.
 */
public class ToDo extends Task {

    /**
     * Class constructor for ToDo.
     *
     * @param status either DONE or NOT_DONE.
     * @param task   task description.
     */
    public ToDo(Status status, String task) {
        super(status, task);
    }

    /**
     * Converts ToDo to the correct string format to write to the data file.
     *
     * @return string to write to data file.
     */
    @Override
    public String convertTask() {
        return "T | " + super.getStatus() + " | " + super.getTask();
    }

    /**
     * Returns string representation of a ToDo object.
     *
     * @return string ToDo.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
