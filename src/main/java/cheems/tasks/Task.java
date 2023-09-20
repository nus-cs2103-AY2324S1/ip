package cheems.tasks;

/**
 * An abstract class that represents each task.
 * Each task has a string description and a status.
 */
public abstract class Task {
    private String description;
    protected boolean isDone;
    public abstract String toStorage();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    /** Marks this task as done.*/
    public void markAsDone() {
        this.isDone = true;
    }

    /** Marks this task as undone.*/
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Formats the task in a user-friendly readable string.
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}

