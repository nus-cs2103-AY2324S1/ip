package task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Unmarks the task
     */
    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[" + getStatusIcon() + "] ");
        sb.append(description);
        return sb.toString();
    }

    public boolean isDeadline() {
        return false;
    }
}

