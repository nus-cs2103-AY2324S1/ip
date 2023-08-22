package pardiyem.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description; // mark done task with X
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
            return "Alright, I've marked this task as undone";
        }
    }
}
