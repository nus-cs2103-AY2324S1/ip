public class Task {
    private boolean isDone;
    private String description;

    public Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    public String getStatusIcon() {
        return (this.isDone ? "/" : " ");
    }

    public void markDone() {
        this.isDone = true;
        System.out.println("I've marked this task as done!\n" + this.toString());
    }

    public void markUndone() {
        this.isDone = false;
        System.out.println("I've marked this task as undone!\n" + this.toString());
    }
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
