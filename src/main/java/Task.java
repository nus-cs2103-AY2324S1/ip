public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTask() {
        return (isDone ? "[X] " + this.description : "[ ] " + this.description); // mark done task with X
    }

    public void setTaskState(boolean isDone) {
        this.isDone = isDone;
    }

}