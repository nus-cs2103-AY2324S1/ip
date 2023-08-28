package state;

public abstract class Task {
    private final String title;
    private final boolean isDone;

    protected Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDone() {
        return isDone;
    }

    public abstract Task mark();

    public abstract Task unmark();

    @Override
    public String toString() {
        String mark = isDone ? "X" : " ";
        return "[" + mark + "] " + title;
    }
}
