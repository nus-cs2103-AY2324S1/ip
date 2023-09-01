package duke.task;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {

        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String contentLine() {
        return "/" + getStatusIcon() + "/" + this.description;
    }

    @Override
    public String toString() {

        String result = "[" + getStatusIcon() + "] " + this.description;
        return result;
    }
}


