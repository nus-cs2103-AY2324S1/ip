package crusader.task;

import java.util.Date;

import crusader.DateUtils;

/**
 * A deadline task, with a time the task is due by
 */
public class Deadline extends Task {
    /**
     * Time the deadline is due by
     */
    private final Date by;

    /**
     * Constructs a new Deadline.
     *
     * @param name  Name of the deadline.
     * @param by    When the deadline is due.
     */
    public Deadline(String name, Date by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format(
                "[D]%s (by: %s)",
                super.toString(),
                DateUtils.presentFormat(this.by));
    }

    @Override
    public String toFormat() {
        return String.format(
                "D|%s|%s|%s",
                super.getName(),
                super.isDone() ? "X" : " ",
                DateUtils.saveFormat(this.by));
    }
}
