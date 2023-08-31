package duke.data.task;
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public boolean getDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean contains(String query) {
        return description.contains(query);
    }

    public String toWrite() {
        if (isDone)
            return "1 | " + description;
        return "0 | " + description;

    }
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
