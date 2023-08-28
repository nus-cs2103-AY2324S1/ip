abstract class Task {
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

    abstract Task done();

    abstract Task undone();

    abstract String storageText();

    protected String getTask() {
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
