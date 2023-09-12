package duke.assets.tasks;

import duke.dukeexceptions.StateCannotBeAlteredException;

/**
 * Abstract class that represents a task
 */
public abstract class TaskAbstract {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new task
     *
     * @param description - description of task
     */
    public TaskAbstract(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as complete
     * @throws StateCannotBeAlteredException if task is already complete
     */
    public void completeTask() throws StateCannotBeAlteredException {
        if (this.isDone) {
            throw new StateCannotBeAlteredException();
        } else {
            this.isDone = true;
        }
    }

    /**
     * Mark the task as incomplete
     * @throws StateCannotBeAlteredException if task is already incomplete
     */
    public void undo() throws StateCannotBeAlteredException {
        if (!this.isDone) {
            throw new StateCannotBeAlteredException();
        } else {
            this.isDone = false;
        }
    }

    /**
     * Marks a newly created task as complete
     */
    public void completeNewTask() {
        this.isDone = true;
    }

    /**
     * Checks if the task description contains the given token
     * @param token token to be checked against
     * @return true if task description contains token, false otherwise
     */
    public boolean hasToken(String token) {
        return this.description.contains(token);
    }

    /**
     * Helper function to check if task is complete
     *
     * @return true if task is complete, false otherwise
     */
    public boolean isComplete() {
        return isDone;
    }

    /**
     * Print status of the task
     */
    public abstract void printStatus();

    /**
     * Formats the task into a string that is ready for saving into memory
     * @return formatted string that is ready for saving into memory
     */
    public abstract String saveToTextFormat();

    @Override
    public String toString() {
        return this.description;
    }
}
