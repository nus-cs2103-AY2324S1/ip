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

    @Override
    public String toString() {
        return this.description;
    }

    public void mark() {
        System.out.println("Nice! I've marked this task as done:");
        this.isDone = true;
        String res = String.format("[X] %s", this.toString());
        System.out.println(res);
    }

    public void unmark() {
        System.out.println("OK, I've marked this task as not done yet:");
        this.isDone = false;
        String res = String.format("[ ] %s", this.toString());
        System.out.println(res);
    }

}
