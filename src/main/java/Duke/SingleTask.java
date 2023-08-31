package Duke;

public abstract class SingleTask {
    protected  String description;
    protected Boolean isDone;
    public SingleTask(String s) {
        this.description = s;
        this.isDone = false;
    }

    public abstract void mark();
    public abstract String getStatusIcon();

    public abstract void unmark();
    public abstract String listString();

    public abstract String remove ();
    public abstract String toSaveString();
}
