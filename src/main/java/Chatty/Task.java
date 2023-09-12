package chatty;
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public abstract String parse();
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void switchCheck() {
        this.isDone = !this.isDone;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}
