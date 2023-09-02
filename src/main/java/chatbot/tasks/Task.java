package chatbot.tasks;

/**
 * Represents an task
 */
public class Task {
    /**
     * Description of task
     */
    protected String description;
    /**
     * Indicates whether the task is done or not
     */
    protected boolean isDone;

    /**
     * Constructor of Task
     * @param description Description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a status icon to represent the task's completion status
     * @return Status icon to represent the task's completion status
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as undone
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the formatted string representation of the task
     * @return Formatted string representation of the task
     */
    public String store() {
        return " ";
    }

    /**
     * Returns the formatted string representation of the task
     * @return Formatted string representation of the task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description ;
    }
}

