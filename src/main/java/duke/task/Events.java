package duke.task;

import java.time.LocalDateTime;
import duke.main.DateFormatter;

/**
 * A Task with starting date and deadline
 */
public class Events extends Task {

    /**
     * The starting date of a task
     */
    protected LocalDateTime startDate;

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
     * @param description The description of the task with starting date and deadline
     * @param startDate The starting date of a task
     * @param endDate The deadline of a task
     */
    public Events (String description, String startDate, String endDate) {
        super(description);
        this.startDate = DF.stringToDate(startDate);
        this.endDate = DF.stringToDate(endDate);
    }

    @Override
    public String getTypeIcon() {
        return "E";
    }

    @Override
    public String printTask() {
        if (this.startDate.toLocalDate().equals(this.endDate.toLocalDate())) {
            return "[" + this.getTypeIcon() + "]" + this.getStatusIcon() + this.description + " (from: " + DF.dateTimeToDate(this.startDate) +
                    " " + DF.dateTimeToTime(this.startDate) + " to: " + DF.dateTimeToTime(this.endDate) + ")";
        }
        return "[" + this.getTypeIcon() + "]" + this.getStatusIcon() + this.description +
                " (from: " + DF.dateToString(this.startDate) + " to: " + DF.dateToString(this.endDate) + ")";
    }


}
