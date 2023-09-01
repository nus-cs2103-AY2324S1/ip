package duke.task;

/**
 * The abstract class for Task.
 * @author Toh Li Yuan (A0255811H)
 */
public abstract class Task {
    protected static final String DISCRIMINATOR = " || ";
    protected String name;
    protected boolean status;
    protected TaskTypes type;

    /**
     * Marks the task as done.
     *
     * @return this Task that has been marked as done.
     */
    public Task mark() {
        this.status = true;
        return this;
    }

    /**
     * Marks the task as not done.
     *
     * @return this Task that has been marked as not done.
     */
    public Task unmark() {
        this.status = false;
        return this;
    }

    @Override
    public String toString() {
        String statusMark = this.status ? "[✓]" : "[✕]";
        return String.format("%s %s", statusMark, name);
    }

    public abstract String toSave();
}
