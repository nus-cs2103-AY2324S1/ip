package duke.task;

/**
 * This class encapsulates a Task.
 */
public class Task {
    protected String taskDescription;
    protected boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    /**
     * Marks the task as completed.
     */
    public void doTask() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as completed.
     */
    public void undoTask() {
        this.isDone = false;
    }

    /**
     * Returns the completed status of the task.
     */
    public String getMarkedIcon() {
        String markedIcon = isDone ? "X" : " ";

        return markedIcon;
    }

    /**
     * Returns the task's description.
     * @return Task's description
     */
    public String getTaskDescription() {
        return this.taskDescription;
    }

    @Override
    public String toString() {
        assert (taskDescription != null) : "The task description for a Task cannot be null.";

        String markedIcon = getMarkedIcon();

        return "[" + markedIcon + "] " + this.taskDescription;
    }
}
