abstract public class Task {
    private String action;
    private boolean completed;

    public Task(String action) {
        this.action = action;
        this.completed = false;
    }
    @Override
    public String toString() {
        return "[" + (this.completed ? "X" : " ") + "] " + this.action + " ";
    }

    public void completeTask() {
        this.completed = true;
    }
    public boolean isCompleted() { return this.completed; }
    public void revertTask() {
        this.completed = false;
    }
}
