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

    public void markAsDone() {
        this.isDone = true;
        System.out.format("______________________________________\n"
        + "Someone's productive. Marked it done for you:\n"
        + " [%s]" + " %s\n"
        + "______________________________________\n", this.getStatusIcon(), this.description);
    }

    public void markAsUndone() {
        this.isDone = false;
        System.out.format("______________________________________\n"
                + "What happened to being productive? Marked it as not done yet:\n"
                + " [%s]" + " %s\n"
                + "______________________________________\n", this.getStatusIcon(), this.description);
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}