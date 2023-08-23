public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return description;
    }

    public void printTask() {
        System.out.println(description);
    }

    public void markAsDone() {
        this.isDone = true;
    }
}
