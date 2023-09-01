package sisyphus.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

     public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone(boolean state) {
        this.isDone = state;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " +  this.description;
    }

    public abstract String toSaveFormat();
}
