package duke.tasks;

import duke.exceptions.IncompleteDescriptionException;

/**
 * Abstract class encapsulating information about a task.
 */
abstract public class Task {
    protected boolean isDone;
    protected String taskName;

    /**
     * Constructor to initialise a task object.
     *
     * @param taskName The name of the task.
     * @throws IncompleteDescriptionException If the task name is left blank.
     */
    protected Task(String taskName) throws IncompleteDescriptionException {
        if (taskName.isBlank()) {
            throw new IncompleteDescriptionException();
        }
        isDone = false;
        this.taskName = taskName;
    }

    /**
     * Marks the task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Un-marks the task as done.
     */
    public void unMarkDone() {
        this.isDone = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    /**
     * A method to compress the data in the task into a format to be stored in the hard disk.
     *
     * @return A string containing the task inforamtion.
     */
    public abstract String compressData();

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }
}
