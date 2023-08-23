public class Task {
    private static int totalTasks = 0;
    private int taskId;
    private String taskName;
    private boolean done;

    public Task(String taskName) {
        totalTasks++;
        this.taskId = totalTasks;
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

    public String toStringId() {
        String status = this.done? "[X]" : "[ ]";
        return this.taskId + "." + this.toString();
    }
}
