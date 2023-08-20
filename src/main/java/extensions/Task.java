package extensions;

public class Task {
    protected String desc;
    protected boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    protected boolean markAsDone() {
        this.isDone = true;
        return true;
    }

    protected boolean unmark() {
        this.isDone = false;
        return true;
    }

    @Override
    public String toString() {
        String output = "[" + getStatusIcon() + "] " + this.desc;
        return output;
    }

}
