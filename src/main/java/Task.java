public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
    }
    public void markAsDone() {
        isDone = true;
    }
    public void markAsUndone() {
        isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String toTxtString() {
        return "";
    }
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " +  this.description;
    }
}
