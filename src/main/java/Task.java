/*
 * Abstract class that represents a general task.
 */
abstract class Task {
    private boolean isDone;
    private String task;

    public Task(String task) {
        this.isDone = false;
        this.task = task;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String status = isDone ? "[X]" : "[ ]";
        return status + " " + task;
    }
}