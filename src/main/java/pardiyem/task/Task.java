package pardiyem.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        if (description.isEmpty()) {
            throw new IllegalArgumentException("Whoops, a task needs to have a non-empty description!");
        }
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String description) throws IllegalArgumentException {
        this(description, false);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    public String markAsDone() {
        if (this.isDone) {
            return "Hey, just letting you know that the task has already been done previously :)";
        } else {
            this.isDone = true;
            return "Bellisimo! I've marked this task as done!";
        }
    }

    public String markAsUndone() {
        if (!this.isDone) {
            return "Hey, just letting you know that the task has already been marked as undone previously:)";
        } else {
            this.isDone = false;
            return "Va bene, I've marked this task as undone";
        }
    }
}
