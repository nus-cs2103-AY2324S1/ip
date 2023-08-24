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

    public void mark() {
        this.isDone = true;
        Duke.display("Nice! I've marked this task as done:\n" + this.toString());
    }

    public void unmark() {
        this.isDone = false;
        Duke.display("OK, I've marked this task as not done yet:\n" + this.toString());
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
