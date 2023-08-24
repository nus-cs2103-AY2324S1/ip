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
        String res = String.format("[%s] %s", getStatusIcon(), this.description);
        return res;
    }

    public void mark() {
        System.out.println("Nice! I've marked this task as done:");
        this.isDone = true;

        System.out.println(this.toString());
    }

    public void unmark() {
        System.out.println("OK, I've marked this task as not done yet:");
        this.isDone = false;

        System.out.println(this.toString());
    }


}
