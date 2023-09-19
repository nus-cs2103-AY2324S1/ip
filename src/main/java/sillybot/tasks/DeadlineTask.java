package sillybot.tasks;

import java.time.LocalDate;

import sillybot.DatesAndTimesFormatter;

/**
 * Represents a DeadlineTask object that is a Task.
 */
public class DeadlineTask extends Task {
    protected String by;
    protected LocalDate date;

    /**
     * Creates a DeadlineTask object.
     *
     * @param description The description of the DeadlineTask.
     * @param by          The date of the DeadlineTask.
     */
    public DeadlineTask(String description, String by) {
        super(description);
        this.by = by;
        this.date = Task.parseDate(by);

        if (date != null) {
            this.by = date.format(DatesAndTimesFormatter.OUTPUT_FORMAT.formatter);
        }
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.by + ")";
    }
}
