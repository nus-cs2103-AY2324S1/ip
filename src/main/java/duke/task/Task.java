package duke.task;

/**
 * A task, its description and its done status.
 */
public class Task {
    /**
     * Stores description and done status of the task.
     */
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a new Task.
     * @param description Description of a task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task's done status
     * @return "X" if done, " " if not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a task as done.
     */
    public String markAsDone() {
        this.isDone = true;
        return "Someone's productive. Marked it done for you:\n"
                + " [" + this.getStatusIcon() + "] " + this.description + "\n";
    }

    /**
     * Marks the task as not done.
     */
    public String markAsUndone() {
        this.isDone = false;
        return "What happened to being productive? Marked it as not done yet:\n"
                + " [" + this.getStatusIcon() + "] " + this.description + "\n";
    }

    /**
     * Returns a string representation of the task description.
     * @return string representation of task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of a task's done status and its description.
     * @return string representation of task
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns string representation of task to be written into save file.
     * @return string representation of task
     */
    public String toWriteString() {
        int mark = (this.isDone ? 1 : 0);
        return mark + " | " + this.description;
    }
}