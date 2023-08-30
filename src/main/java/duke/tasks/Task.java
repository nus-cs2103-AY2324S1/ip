package duke.tasks;

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
    
    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toStorageString() {
        return " | " + ( this.isDone ? 1 : 0 ) + " | " + this.description;
    }
}
