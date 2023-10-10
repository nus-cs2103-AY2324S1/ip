package duke.task;

/**
 * A Task class to represent tasks
 */
public abstract class Task {
    private final String description;
    private boolean isDone;
    private final TaskType type;

    /**
     * Constructs task with the given description and type
     *
     * @param description the given description of the task
     * @param type        the given type of the task
     */
    public Task(String description, boolean isDone, TaskType type) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }

    /**
     * Marks the task as completed by setting its status to 'done'.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks the task as not completed by setting its status to 'not done'.
     */
    public void markUndone() {
        isDone = false;
    }

    /**
     * Returns whether task is done
     */
    public boolean getDone() {
        return this.isDone;
    }

    /**
     * Returns description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns description of task
     */
    public String getType() {
        return this.type.toString();
    }

    /**
     * Abstract method to check if two tasks are equal.
     *
     * @param otherTask the task to compare to
     * @return true if the tasks are equal, false otherwise
     */
    public abstract boolean isEqual(Task otherTask);


    /**
     * Returns String representing the task
     *
     * @return String representing task
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + type + ": " + description;
    }
}
