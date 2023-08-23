package Tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new task
     *
     * @param description - description of task
     */
    public Task(String description) {
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

    @Override
    public String toString() {
        return this.description;
    }
}
