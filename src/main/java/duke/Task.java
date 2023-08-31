package duke;

import java.io.Serializable;

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }
    public void mark() {
        this.isDone = true;
    }
    public void unmark() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return this.getDescription();
    }
}