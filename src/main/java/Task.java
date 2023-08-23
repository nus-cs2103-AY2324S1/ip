public class Task {
    private String task;
    private Boolean completed = false;
    public Task(String task) {
        this.task = task;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String toString() {
        String box = this.completed ? "[X]": "[ ]";
        return box + " " + this.task;
    }
}
