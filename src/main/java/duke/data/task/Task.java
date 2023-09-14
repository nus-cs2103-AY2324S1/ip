package duke.data.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        assert this.description != null: "The description of the task is null";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    /** Marks task as done by setting isDone to true */
    public void mark() {
        this.isDone = true;
    }

    /** Marks task as undone by setting isDone to false */
    public void unmark() {
        this.isDone = false;
    }

    public String saveString() {
        return String.format(" | %s | %s", isDone ? 1 : 0, description);
    }
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}
