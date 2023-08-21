public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with description
     * @param description Name of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns icon indicating task's status
     * @return "X" if task is done, " " otherwise
     */
    public String getStatusIcon() {
        return this.isDone? "X" : " ";
    }

    /**
     * Returns task's name
     * @return The name of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks task as done
     */
    public void checkTask() {
        this.isDone = true;
    }

    /**
     * Marks task as not done
     */
    public void uncheckTask() {
        this.isDone = false;
    }

    /**
     * String representation of task
     * @return String representation of task containing task status and task description
     */
    public String toString() {
        return String.format("[%s] %s\n", this.getStatusIcon(), this.getDescription());
    }
}
