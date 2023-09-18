package aj;


/**
 * Task abstract class for user tasks.
 */
public abstract class Task {

    protected boolean isMarked;
    protected String taskName;

    Task(String taskName, boolean completed) {
        this.taskName = taskName;
        this.isMarked = completed;
    }

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
}
