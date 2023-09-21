package duke.tasks;

/**
 * A class to represent tasks.
 */
public class Task {
    private String description;
    private Boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Marks a task as done.
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * Marks a task as not done.
     */
    public void markUndone() {
        this.done = false;
    }

    /**
     * Sets whether the task is done.
     * @param done
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Returns a string representing the description of the task.
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a boolean representing whether the task is done.
     * @return done
     */
    public boolean getDone() {
        return this.done;
    }

    @Override
    public String toString() {
        return (done ? "[X] " : "[ ] ") + this.getDescription();
    }
}

