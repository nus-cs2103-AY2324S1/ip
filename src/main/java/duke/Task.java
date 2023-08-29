package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    protected String tag;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tag = "R";
    }

    public Task(String description, String tag) {
        this.description = description;
        this.isDone = false;
        this.tag = tag;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public String generalTag() {
        return this.tag;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
