package pau.task;

public abstract class Task {
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
    public void markAsUndone() {
        this.isDone = false;
    }

    public abstract String writeToFile();

    public void setStatus(String status) {
        if ((status == "1")) {
            this.markAsDone();
        } else {
            this.markAsUndone();
        }
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
