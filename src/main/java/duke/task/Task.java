package duke.task;
/**
 * Task class for the Richie application
 */

public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for a task which initialises the task description and whether it is already done
     * @param description Represents the task name
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon for the task, if the task is done the icon is an X if not it is empty
     * @return X if task is done, space if task is not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a string representation of the task which includes whether the task is already done
     * @return A string that includes the task status icon and its description
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Marks a task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the string description of the task
     * @return String description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether the task has been done
     * @return boolean representing whether the task has been done
     */
    public boolean getIsDone() {
        return this.isDone;
    }


}