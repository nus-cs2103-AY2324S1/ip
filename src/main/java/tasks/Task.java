package tasks;

import java.io.Serializable;

public abstract class Task implements Serializable {
    private String taskName;
    private TaskType taskType;
    private boolean isDone;

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
        String taskStatus = isDone
                ? "[X]"
                : "[ ]";
        return String.format("[%s]%s %s", taskType.getTaskType(), taskStatus, taskName);
    }
}
