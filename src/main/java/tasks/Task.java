package tasks;

public abstract class Task {
    protected String desc;
    protected boolean isDone;

    protected abstract String getTextFormattedString();

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
        String output = String.format("[%s] %s", getStatusIcon(), this.desc);
        return output;
    }

}
