package tasks;

public class Task {
    protected String desc;
    protected boolean status;

    public Task(String desc) {
        this.desc = desc;
        this.status = false;
    }

    public void markAsDone() {
        this.status = true;
    }

    public void markAsNotDone() {
        this.status = false;
    }

    public String getStatusIcon() {
        return (this.status ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.desc;
    }
}
