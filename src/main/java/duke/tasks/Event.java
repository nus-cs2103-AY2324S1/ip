package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task {

    protected LocalDate fromDate;
    protected LocalTime fromTime;
    protected LocalDate toDate;
    protected LocalTime toTime;

    public Event(String description, boolean isDone, LocalDate fromDate, LocalTime fromTime, LocalDate toDate,
            LocalTime toTime) {
        super(description, isDone);
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
    }

    /**
     * Returns a String representation of this Status. The format is
     * "[E][StatusIcon] Description (from: Date/Time to: Date/Time)"
     * 
     * @return a String representation of this Event.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s %s to: %s %s)", getStatusIcon(), description,
                (fromDate != null ? fromDate : ""), (fromTime != null ? fromTime : ""),
                (toDate != null ? toDate : ""), (toTime != null ? toTime : ""));
    }

    /**
     * Returns a string representation of this Event. The format is E # Doneness #
     * Description # Date/Time # Date/Time.
     * Note that this is different from toString() as it is used for encoding data
     * in the file.
     * 
     * @return a string representation of this Event for storage in the file.
     */
    @Override
    public String toFileString() {
        return String.format("E # %d # %s # %s %s # %s %s", (isDone ? 1 : 0), description,
                (fromDate != null ? fromDate : ""), (fromTime != null ? fromTime : ""),
                (toDate != null ? toDate : ""), (toTime != null ? toTime : ""));
    }
}
