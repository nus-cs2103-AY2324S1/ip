package tasks;

import java.util.Objects;

public abstract class Task {
    protected String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task otherTask = (Task) o;
        return this.isDone == otherTask.isDone && this.description.equals(otherTask.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, isDone);
    }
}