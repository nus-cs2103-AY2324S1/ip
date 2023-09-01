package duke.task;

/**
 * A Task class to represent tasks
 */
public class Task {
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
     * Method that marks tasks as done
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Method that changes back the task status to not done
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
     * Returns String representing the task
     *
     * @return String representing task
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + type + ": " + description;
    }
}