package aichan.task;

public class Task {
    private String taskName;
    private boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public String toString() {
        String status = this.done? "[X]" : "[ ]";
        return status + " " + this.taskName;
    }
}
