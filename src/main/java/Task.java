public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markDone() {
        this.isDone = true;
    }
    public boolean getStatus() {
        return this.isDone;
    }

    public String getName() {
        return this.taskName;
    }

    public void markUndone() {
        this.isDone = false;
    }
    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.taskName;
    }
}
