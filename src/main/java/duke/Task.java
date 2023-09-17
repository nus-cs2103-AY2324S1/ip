package duke;

/**
 * Tasks to be added to tasklist
 */
public class Task {
    /** Description of task */
    protected String description;

    /** To access whether a task is completed */
    protected boolean isDone;

    /** Task to be added to list
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get icon depending on whether the task is completed
     *
     * @return "X" for completed task, " " for uncompleted task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); //mark done task with X
    }

    /**
     * Get icon depending on whether the task is completed,
     * in storage form
     *
     * @return "1" for completed task, "0" for uncompleted task.
     */
    public String getStatusToStore() {
        return (isDone ? "1" : "0");
    }

    /**
     * To mark a task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * To mark a task as undone
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Gets String of the task with its corresponding status
     *
     * @return String of status and description of task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
