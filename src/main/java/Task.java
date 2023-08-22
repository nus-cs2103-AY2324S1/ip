public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription(){
        return description;
    }

    public void taskDone(){
        isDone = true;
    }

    public void undoTask() {
        isDone = false;
    }
}
