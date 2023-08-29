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

    public void mark() {
        isDone = true;
        System.out.println("____________________________________________________________");
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + this);
        System.out.println("____________________________________________________________");
    }
    public void unmark() {
        isDone = false;
        System.out.println("____________________________________________________________");
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + this);
        System.out.println("____________________________________________________________");
    }
    public void markDone() {
        isDone = true;
    }
    @Override
    public String toString() {
        return " [" + this.getStatusIcon() + "] " + this.description;
    }
}