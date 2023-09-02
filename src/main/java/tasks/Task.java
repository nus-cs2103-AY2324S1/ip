package tasks;

/**
 * The class representing a Task.
 *
 * @author Gallen Ong
 */
public class Task {
    protected String description;
    protected boolean isDone;

<<<<<<< HEAD
    /**
     * Initialises a Task object with a task description.
     *
     * @param description
     */
=======
>>>>>>> branch-A-CodingStandard
    public Task(String description) {
        this.description = description;
    }

    /**
     * Returns the symbol corresponding to the Task status.
     *
     * @return The status icon of the Task.
     */
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

<<<<<<< HEAD
    /**
     * Updates the status of the Task as done.
     */
=======
    public String getDescription() {
        return this.description;
    }

>>>>>>> branch-Level-9
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Updates the status of the Task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the Task.
     *
     * @return The Task in string format.
     */
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

<<<<<<< HEAD
    /**
     * Returns a string representation of the Task to be added to a file.
     *
     * @return The Task in string format, specific for file operations.
     */
=======
>>>>>>> branch-A-CodingStandard
    public String toStringForFile() {
        String status = isDone ? "1" : "0";
        return status + " | " + description;
    }
}
