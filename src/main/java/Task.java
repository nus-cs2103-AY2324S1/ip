public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String taskDescription() {
        return (isDone ? "[X] " + this.description : "[ ] " + this.description); // mark done task with X
    }
    @Override
    public String toString() {
        return "[T]" + taskDescription();
    }
    public void setTaskState(boolean isDone) {
        this.isDone = isDone;
    }

}