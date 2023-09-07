package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A task that has a starting and ending time.
 */
public class Event extends Task {
    private final LocalDate startDate;
    private final LocalTime startTime;
    private final LocalDate endDate;
    private final LocalTime endTime;

    /**
     * Creates an event task instance.
     *
     * @param description Description of the task.
     * @param start Starting time of the task.
     * @param end Ending time of the task.
     */
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.startDate = start;
        this.endDate = end;
        this.startTime = null;
        this.endTime = null;
    }

    public Event(String description, LocalDate start, LocalTime startTime, LocalDate end) {
        super(description);
        this.startDate = start;
        this.endDate = end;
        this.startTime = startTime;
        this.endTime = null;
    }

    public Event(String description, LocalDate start, LocalDate end, LocalTime endTime) {
        super(description);
        this.startDate = start;
        this.endDate = end;
        this.startTime = null;
        this.endTime = endTime;
    }
    public Event(String description, LocalDate start, LocalTime startTime, LocalDate end, LocalTime endTime) {
        super(description);
        this.startDate = start;
        this.endDate = end;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return Desired string representation of the task.
     */
    @Override
    public String convertToString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[E] " + super.convertToString() + " (from: " + this.startDate.format(dateFormat)
                + this.convertStartTimeToString() + " to: "
                + this.endDate.format(dateFormat) + this.convertEndTimeToString()+ ")";
    }

    private String convertStartTimeToString() {
        return (this.startTime != null)
                ? " " + this.startTime.format(DateTimeFormatter.ofPattern("h:mma"))
                : "";
    }

    private String convertEndTimeToString() {
        return (this.endTime != null)
                ? " " + this.endTime.format(DateTimeFormatter.ofPattern("h:mma"))
                : "";
    }

    /**
     * Returns a string representation of the task to be inserted into a file.
     *
     * @return Desired string representation of the task.
     */
    @Override
    public String convertToStringInFile() {
        return "[E] /" + super.convertToStringInFile() + " / " + this.startDate
                + this.convertStartTimeToStringInFile() + " / " + this.endDate
                + this.convertEndTimeToStringInFile();
    }

    private String convertStartTimeToStringInFile() {
        return this.startTime != null
                ? " / " + this.startTime.format(DateTimeFormatter.ofPattern("HH:mm"))
                : "";
    }

    private String convertEndTimeToStringInFile() {
        return this.endTime != null
                ? " / " + this.endTime.format(DateTimeFormatter.ofPattern("HH:mm"))
                : "";
    }
}
