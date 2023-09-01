package juke;

public class Task {
    protected final String desc;
    protected boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    /**
     * Gets the appropriate icon based on whether the task is done.
     * @return [X] if task is done, [ ] if task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + desc;
    }

    /**
     * Generates string to be stored as data in Storage.
     * @return String which stores all necessary attributes in the task.
     */
    public String toData() {
        return "|" + isDone + "|" + desc;
    }
}
