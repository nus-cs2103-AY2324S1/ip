package duke.tasks;

import java.io.Serializable;

/**
 * Represents a general task in the Duke application.
 */
public abstract class Task implements Serializable {
    private String taskName;
    private TaskType taskType;
    private boolean isDone;

    /**
     * Constructs a Task.
     *
     * @param taskName The name of the task.
     * @param taskType The type of the task (e.g., TODO, DEADLINE).
     */
    public Task(String taskName, TaskType taskType) {
        this.taskName = taskName;
        this.taskType = taskType;
        this.isDone = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String taskStatus = isDone ? "[X]" : "[ ]";
        return String.format("[%s]%s %s", taskType.getTaskType(), taskStatus, taskName);
    }
}