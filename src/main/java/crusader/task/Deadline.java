package crusader.task;

import crusader.DateUtils;

import java.util.Date;

/**
 * A deadline task, with a time the task is due by
 */
public class Deadline extends Task {
    /**
     * Time the deadline is due by
     */
    private final Date by;

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
