package Tasks;

public class Task {
    protected String description;
    //    protected TaskType type;
    protected boolean isDone;

    //    public Task(String description, TaskType taskType) {
    public Task(String description) {
        this.description = description.trim();
//        this.type = taskType;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }
}