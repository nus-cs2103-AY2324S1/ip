public class Task {
    protected String description;
    protected boolean isDone;
    protected String cat;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.cat = "";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); //mark done task with X
    }

    public void markAsDone() { isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
