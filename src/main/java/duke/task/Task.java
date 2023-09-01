package duke.task;

public class Task {
    protected String description;
    private static final String LINE = "____________________________________________________________";
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void markDone() {
        this.isDone = true;
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
        System.out.println(LINE);
    }

    public void unmarkDone() {
        this.isDone = false;
        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.toString());
        System.out.println(LINE);
    }
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}

