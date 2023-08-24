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

    public String getDescription() {
        return description;
    }
    public void mark() {
        isDone = true;
        System.out.println("____________________________________________________________");
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   [" + this.getStatusIcon() + "] " + description);
        System.out.println("____________________________________________________________");
    }
    public void unmark() {
        isDone = false;
        System.out.println("____________________________________________________________");
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   [" + this.getStatusIcon() + "] " + description);
        System.out.println("____________________________________________________________");
    }
}