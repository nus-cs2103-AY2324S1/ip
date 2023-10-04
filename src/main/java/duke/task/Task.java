package duke.task;

import java.time.LocalDate;

/**
 * The abstract class for Task.
 * @author Toh Li Yuan (A0255811H)
 */
public abstract class Task {
    public static final String DISCRIMINATOR = " || ";
    protected String name;
    protected boolean isDone;
    protected TaskTypes type;

    /**
     * Marks the task as done.
     *
     * @return this Task that has been marked as done.
     */
    public Task mark() {
        this.isDone = true;
        return this;
    }

    /**
     * Marks the task as not done.
     *
     * @return this Task that has been marked as not done.
     */
    public Task unmark() {
        this.isDone = false;
        return this;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        String statusMark = this.isDone ? "[✓]" : "[✕]";
        return String.format("%s %s", statusMark, name);
    }

    public abstract String toSave();

    public abstract String getReminder(LocalDate currDate, int days);
}
