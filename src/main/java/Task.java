public class Task {
    protected String taskDescription;
    protected boolean isDone;

    public Task(String taskDescription, boolean isDone) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public void changeStatus() {
        if (this.isDone) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
    }
}
