package duke.assets.tasks;

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
     * Completes the task
     */
    public void completeTask() {
        this.isDone = true;
    }

    /**
     * Undo a task which was marked as complete
     */
    public void undo() {
        this.isDone = false;
    }

    /**
     * Print status of the task
     */
    public abstract void printStatus();

    /**
     * Helper function to check if task is complete
     *
     * @return - true if task is complete, false otherwise
     */
    public boolean isComplete() {
        return isDone;
    }

    public abstract String saveToTextFormat();

    @Override
    public String toString() {
        return this.description;
    }
}
