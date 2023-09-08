package duke.task;

import java.time.LocalDateTime;

import duke.main.DateFormatter;

/**
 * A Task with deadline.
 */
public class Deadlines extends Task {
    /**
     * A formatter for the date
     */
    private static final DateFormatter DF = new DateFormatter();

    /**
     * The deadline of a task
     */
    protected LocalDateTime endDate;

    /**
     * The constructor for Deadlines
     * @param description The description of the task with deadline
     * @param endDate The deadline of a task
     */
    public Deadlines(String description, String endDate) {
        super(description);
        this.endDate = DF.stringToDate(endDate);
    }

    @Override
    public String getTypeIcon() {
        return "D";
    }

    @Override
    public String printTask() {
        return "[" + this.getTypeIcon() + "]" + this.getStatusIcon() + this.description
                + " (by: " + DF.dateToString(this.endDate) + ")";
    }
}
