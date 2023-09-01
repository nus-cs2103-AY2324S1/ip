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

    public String getStatusNumber() {
        return (isDone ? "1" : "0"); // mark done task with 1
    }


    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String formatToSave() {
        return " | " + getStatusNumber() + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() +"] " + this.description;
    }
}
