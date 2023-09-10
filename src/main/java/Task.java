public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markStatus() {
        this.isDone = true;
    }

    public void unmarkStatus() {
        this.isDone = false;
    }


    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}