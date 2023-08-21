public class Task {
    private String taskName;
    private boolean completed;
    Task(String taskName) {
        this.taskName = taskName;
        completed = false;
    }
    public boolean isCompleted() {
        return this.completed;
    }
    public void mark() {
        this.completed = true;
    }
    public void unmark() {
        this.completed = false;
    }
    @Override
    public String toString() {
        if (this.isCompleted()) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }
}
