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

    /**
     * Checks if the task description contains a specified keyword.
     *
     * @param keyword The keyword to search for within the task description.
     * @return `true` if the task description contains the keyword, `false` otherwise.
     */
    public boolean hasKeyWord(String keyword) {
        return this.description.contains(keyword);
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