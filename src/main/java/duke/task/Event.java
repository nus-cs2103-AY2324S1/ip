package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A task that has a starting and ending time.
 */
public class Event extends Task {
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;

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
        assert this.startDate != null : "Start date should not be empty";
        assert this.endDate != null : "End date should not be empty";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[E] " + super.convertToString() + " (from:" + convertDateToString(this.startDate)
                + convertTimeToString(this.startTime) + " to:"
                + convertDateToString(this.endDate) + convertTimeToString(this.endTime)+ ")";
    }

    private String convertDateToString(LocalDate d) {
        return (d != null)
                ? " " + d.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                : "";
    }

    private String convertTimeToString(LocalTime t) {
        return (t != null)
                ? " " + t.format(DateTimeFormatter.ofPattern("h:mma"))
                : "";
    }

    /**
     * Returns a string representation of the task to be inserted into a file.
     *
     * @return Desired string representation of the task.
     */
    @Override
    public String convertToStringInFile() {
        assert this.startDate != null : "Start date should not be empty";
        assert this.endDate != null : "End date should not be empty";
        return "[E] /" + super.convertToStringInFile() + " / " + convertDateToStringInFile(this.startDate)
                + convertTimeToStringInFile(this.startTime) + " / " + convertDateToStringInFile(this.endDate)
                + convertTimeToStringInFile(this.endTime);
    }

    private String convertDateToStringInFile(LocalDate d) {
        return d.toString();
    }

    private String convertTimeToStringInFile(LocalTime t) {
        return t != null
                ? " / " + t.format(DateTimeFormatter.ofPattern("HH:mm"))
                : "";
    }
}
