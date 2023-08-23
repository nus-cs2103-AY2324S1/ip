public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String taskDesc) {
        this.description = taskDesc;
        this.isDone = false;
    }

    public void isCompleted() {
        isDone = true;
    }

    public void isNotCompleted() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
