package taskmaster.tasks;
public class Task {
    /**
     * Description of task.
     */
    protected String description;
    /**
     * Boolean on whether the task is done.
     */
    protected boolean isDone;

    /**
     * Constructor for the task class.
     * Tasks can be set to either done or not done.
     *
     * @param description Name of the task.
     * @param marked String to indicate whether the task is done
     */
    public Task(String description, String marked) {
        this.description = description;
        if (marked == "marked") {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }
    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }
    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }
    /**
     * Returns a string representation of the task.
     *
     * @return A string representing the task.
     */
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

    /**
     * Returns description of task.
     * @return String of description of task.
     */
    public String getDescription() {
        return this.description;
    }
}
