package smolbrain.task;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getStatusNumber() {
        return (isDone ? "1" : "0");
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String encode() { return ""; }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

}