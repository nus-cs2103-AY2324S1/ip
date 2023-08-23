public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unMark() {
        this.isDone = false;
    }


    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
