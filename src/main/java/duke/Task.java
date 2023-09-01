package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void mark(boolean flag) {
        if (!flag) {
            this.isDone = true;
        } else {
            System.out.println("Nice! I've marked this task as done:\n");
            this.isDone = true;
            System.out.println(this);
        }
    }

    public void unmark() {
        System.out.println("OK, I've marked this task as not done yet:\n");
        this.isDone = false;
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
