class Task {
    private final String task;
    private final boolean isDone;

    Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    Task done() {
        return new Task(this.task, true);
    }

    Task undone() {
        return new Task(this.task, false);
    }

    String getTask() {
        return this.task;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + this.task;
        }
        return "[ ] " + this.task;
    }
}
