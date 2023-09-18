package duke.task;

import duke.Date;
import duke.Parser;
import duke.exceptions.InvalidDateTimeFormatException;

/**
 * A task with both an ending deadline as well as a starting period
 */
public abstract class TaskWithPeriod extends TaskWithDeadline {
    protected Date from;
    public TaskWithPeriod(String description, String from, String to) throws InvalidDateTimeFormatException {
        super(description, to);
        this.from = new Parser().parseDate(from);
    }

    public Date getFrom() {
        return this.from;
    }

    public String getFromSave() {
        return this.from.toSave();
    }
}
