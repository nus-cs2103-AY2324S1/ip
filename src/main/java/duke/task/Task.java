package duke.task;

public abstract class Task {
    private String description;
    private boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String msg = "[" + (isDone ? "X" : " ") + "]";
        msg = msg + " " + this.description;
        return msg;
    }
}
