package aj;

public abstract class Task {
    protected boolean isMarked;
    protected String taskName;


    public void toggleComplete() {
        this.isMarked = !this.isMarked;
    }

    public boolean isCompleted() {
        return this.isMarked;
    }

    public String getTaskName() {
        return this.taskName;
    }


    @Override
    public String toString() {
        return (this.isMarked ? "[X] " : "[ ] ") + this.taskName;
    }

    Task(String taskName, boolean completed) {
        this.taskName = taskName;
        this.isMarked = completed;
    }
}
