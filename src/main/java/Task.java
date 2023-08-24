
public class Task {
    protected boolean isComplete;
    protected String description;
    protected static int numOfTasks = 0;
    protected int id;

    public Task(String description){
        this.description = description;
        this.isComplete = false;
        Task.numOfTasks++;
        this.id = numOfTasks;

    }

    public void markDone() {
        isComplete = true;
    }

    public void markUndone() {
        isComplete = false;
    }

    public String getStatusIcon() {
        return (isComplete ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
