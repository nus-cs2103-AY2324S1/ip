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
        if (this.isDone) {
            System.out.println("It has already been done");
        } else {
            this.isDone = true;
            System.out.println("I've marked this task as done!\n" + this);
        }
    }

    public void markUndone() {
        if (!this.isDone) {
            System.out.println("It was already undone");
        } else {
            this.isDone = false;
            System.out.println("I've marked this task as undone!\n" + this);
        }
    }
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
