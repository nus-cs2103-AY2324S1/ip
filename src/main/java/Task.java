public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(boolean bool) {
        isDone = bool;
    }
}
