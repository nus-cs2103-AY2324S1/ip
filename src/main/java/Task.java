abstract public class Task {
    protected String action;
    protected boolean completed;

    public Task(String action) {
        this.action = action;
        this.completed = false;
    }

    public void completeTask() {
        this.completed = true;
    }

     public abstract void displayTask(int index);

    public void revertTask() {
        this.completed = false;
    }
}
