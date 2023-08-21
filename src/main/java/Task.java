public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String printTask() {
        return "[" + (isDone ? "X" : " ") + "] " + this.description;
    }
}
