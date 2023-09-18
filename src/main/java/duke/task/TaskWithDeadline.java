package duke.task;

import duke.Date;
import duke.Parser;
import duke.exceptions.InvalidDateTimeFormatException;

/**
 * A task with a ending deadline to be met
 */
public abstract class TaskWithDeadline extends Task {
    protected Date end;
    public TaskWithDeadline(String description, String by) throws InvalidDateTimeFormatException {
        super(description);
        this.end = new Parser().parseDate(by);
    }

    public Date getEnd() {
        return this.end;
    }

    public String getEndSave() {
        return this.end.toSave();
    }
}
