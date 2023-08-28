public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    public String getTypeIcon() {
        return "[ ]";
    }

    public String getExtras() {
        return "";
    }

    public void markTaskDone() {
        isDone = true;
    }

    public void markTaskNotDone() {
        isDone = false;
    }
}