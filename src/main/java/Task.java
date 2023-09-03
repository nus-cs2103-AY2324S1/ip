public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTaskStatus() {
        return (isDone ? "X" : " ");
        // if task is done, mark it with an X
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }
    @Override
    public String toString() {
        return "[" + getTaskStatus() + "] " + description;
    }

    public String writeToFile() {
        return "Error";
    }
}
