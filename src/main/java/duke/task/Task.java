package duke.task;

public abstract class Task {
    private final String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    public String getTaskStatus() {
        return this.isDone ? "[X]" : "[ ]";
    }

    public String getTaskName() {
        return this.taskName;
    }

    public abstract String getTaskType();

    public abstract String getTaskTime();

    public abstract String toSaveFormat();

    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return this.getTaskType() + this.getTaskStatus() + " " + this.getTaskName() + this.getTaskTime();
    }
}
