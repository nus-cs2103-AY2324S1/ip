package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object.
     *
     * @param description  The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the tasks as not dome yet.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task's description for data storage.
     *
     * @return  The task's description.
     */
    public String toDataString() {
        return description;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return  A string representation of the task.
     */
    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
}
