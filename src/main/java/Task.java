import java.io.IOException;

public abstract class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }

    public String toFileString() {
        return this.getStatusIcon() + " | " + this.name;
    }

    public String toString() {
        return name;
    }
}
