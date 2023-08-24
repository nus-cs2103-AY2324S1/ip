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
        System.out.println("The task has been marked as done!");
        System.out.println(this);
    }

    public void markAsUndone() {
        this.isDone = false;
        System.out.println("The task has been marked as undone!");
        System.out.println(this);
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
