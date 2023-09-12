package duke.assets.tasks;

import duke.dukeexceptions.StateCannotBeAlteredException;

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
     * Print status of the task
     */
    public abstract void printStatus();

    /**
     * Helper function to check if task is complete
     *
     * @return true if task is complete, false otherwise
     */
    public boolean isComplete() {
        return isDone;
    }

    /**
     * Formats the task into a string that is ready for saving into memory
     * @return formatted string that is ready for saving into memory
     */
    public abstract String saveToTextFormat();

    @Override
    public String toString() {
        return this.description;
    }

    public boolean hasToken(String token) {
        return this.description.contains(token);
    }
}
