package jerma.tasks;

public abstract class Task {
    protected String symbol;
    private String description;
    private Boolean isDone;

    /**
     * Constructor for a Task
     * 
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets task to done
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Sets task to undone
     */
    public void setUndone() {
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns string representation of Task for saving purposes
     * 
     * @return String representation of Task for saving purposes
     */
    public String save() {
        return String.format("%d|%s", this.isDone.compareTo(false),
                this.description);
    };

    /**
     * Returns string representation of Task
     * 
     * @return String representation of Task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
