public abstract class Task {
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
        this.isDone = true;
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + this);
    }

    public void unmark() {
        this.isDone = false;
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + this);
    }

    public String toStringForSave() {
        int status = this.isDone ? 1 : 0;
        return status + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}