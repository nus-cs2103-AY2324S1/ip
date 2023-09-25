package chat.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

/**
 * A task that has a description, completeness, start date/time and end date/time.
 * @author juzzztinsoong
 */
public class Event extends Task {

    protected LocalDate fromDate;
    protected LocalTime fromTime;
    protected LocalDate toDate;
    protected LocalTime toTime;

    /**
     * Constructor method for Event.
     * @param description the description of the deadline. Cannot be empty.
     * @param isDone true if the deadline is done, false otherwise.
     * @param fromDate the date to use for the event start. Will not be displayed if null.
     * @param fromTime the time to use for the event start. Will not be displayed if null.
     * @param toDate the date to use for the event end. Will not be displayed if null.
     * @param toTime the time to use for the event end. Will not be displayed if null.
     */
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
     * @return a String representation of this Event.
     */
    @Override
    public String toString() {
        String fromDateString = fromDate != null ? fromDate.toString() : "";
        String fromTimeString = fromTime != null ? fromTime.toString() : "";
        String toDateString = toDate != null ? toDate.toString() : "";
        String toTimeString = toTime != null ? toTime.toString() : "";
        return String.format("[E][%s] %s (from: %s %s to: %s %s)", getStatusIcon(), description, fromDateString, fromTimeString, toDateString, toTimeString);
    }

    /**
     * Returns a string representation of this Event. The format is E # Doneness #
     * Description # Date/Time # Date/Time.
     * Note that this is different from toString() as it is used for encoding data
     * in the file.
     * @return a string representation of this Event for storage in the file.
     */
    @Override
    public String toFileString() {
        String fromDateString = fromDate != null ? fromDate.toString() : "";
        String fromTimeString = fromTime != null ? fromTime.toString() : "";
        String toDateString = toDate != null ? toDate.toString() : "";
        String toTimeString = toTime != null ? toTime.toString() : "";
        return String.format("E # %d # %s # %s %s # %s %s", getDoneInt(), description, fromDateString, fromTimeString, toDateString, toTimeString);
    }

    @Override
    public long getFirstDate() {
        LocalDate tempDate = fromDate != null ? fromDate : LocalDate.of(1970,1,1);
        LocalTime tempTime = fromTime != null ? fromTime : LocalTime.of(0,0);
        return LocalDateTime.of(tempDate, tempTime).toEpochSecond(ZoneOffset.UTC);
    }
}
