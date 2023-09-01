public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public void setMarked() {
        this.isDone = true;
    }

    public void notDone() {
        this.isDone = false;
    }

    public String formatFile() {
        return " ";
    }
}
