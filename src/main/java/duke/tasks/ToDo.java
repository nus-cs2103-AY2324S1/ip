package duke.tasks;

import duke.exceptions.IncompleteDescriptionException;

/**
 * A class encapsulating information of a task of ToDo type.
 */
public class ToDo extends Task {

    public ToDo(String taskName) throws IncompleteDescriptionException {
        super(taskName);
    }

    /**
     * Factory method for the ToDo class.
     *
     * @param taskName Name of the task.
     * @return A ToDo task.
     * @throws IncompleteDescriptionException If the task name is left blank.
     */
    public static ToDo create(String taskName) throws IncompleteDescriptionException {
        if (taskName.isBlank()) {
            throw new IncompleteDescriptionException();
        }
        return new ToDo(taskName);
    }

    @Override
    public String compressData() {
        char isDoneChar = this.isDone ? '1' : '0';
        return "T" + " | " + isDoneChar + " | " + this.taskName;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
