package duke;
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.isDone ? "[X] " + this.description: "[ ] " + this.description;
    }

    public String savedString() {
        String status = this.isDone ? "1" : "0";
        return "| " + status + " | " + this.description;
    }
}
