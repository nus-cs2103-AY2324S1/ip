abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println(" ----------------------------------------\n"
                + "  Nice! I've marked this task as done:\n"
                + "     " + this
                + "\n ----------------------------------------");
    }

    public void markAsUndone() {
        this.isDone = false;
        System.out.println(" ----------------------------------------\n"
                + "  OK, I've marked this task as not done yet:\n"
                + "     " + this
                + "\n ----------------------------------------");
    }

    public String getType() {
        return " ";
    }

    public String getDetails() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getType() + "]" + "[" + this.getStatusIcon() + "] " + this.description;
    }
}
