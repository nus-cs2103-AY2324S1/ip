public class Task {
    protected String message;
    protected boolean isDone;

    Task(String message) {
        this.message = message;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String toString() {
        return this.getStatusIcon() + " " + this.message;
    }
}