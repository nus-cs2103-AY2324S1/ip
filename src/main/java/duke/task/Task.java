package duke.task;

public abstract class Task {
    private final String task;
    private final boolean isDone;



    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    public abstract Task done();

    public abstract Task undone();

    public abstract String storageText();

    public String getTask() {
        return this.task;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + this.task.trim();
        }
        return "[ ] " + this.task.trim();
    }
}
