package duke;

/**
 * The SingleTask class represents a single task for the Duke program.
 * It is an abstract class that provides a common interface for different types of tasks.
 */
public abstract class SingleTask {
    protected  String description;
    protected Boolean isDone;
    public SingleTask(String s) {
        this.description = s;
        this.isDone = false;
    }

    public abstract String mark();
    public abstract String getStatusIcon();

    public abstract String unmark();
    public abstract String listString();

    public abstract String remove ();
    public abstract String toSaveString();
}
