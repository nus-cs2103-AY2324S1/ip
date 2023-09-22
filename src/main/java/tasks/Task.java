package tasks;

/**
 * Represents a task.
 */
public class Task {
    private Boolean isDone = false;
    private final String description;
    private Task oldTask;
    private TaskList list;
    
    /**
     * Constructor for Task class from file inputs
     * @param description
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Constructor for Task class from stdin inputs
     * @param description
     * @param list
     */
    public Task(String description, TaskList list) {
        this(description);
        this.list = list;
        this.oldTask = this.list.findDuplicateTask(this.description);
    }

    /**
     * Returns the oldTask already in the list
     * @return String
     */
    public Task getOldTask() {
        return oldTask;
    }

    @Override
    public String toString() {
        return "["
              + (isDone ? "X" : " ")
              + "] "
              + description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }
}
