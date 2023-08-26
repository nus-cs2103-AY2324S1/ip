import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A task that has a starting and ending time.
 */
public class Event extends Task{
    private final LocalDate start;
    private final LocalTime startTime;
    private final LocalDate end;
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
        this.start = start;
        this.end = end;
        this.startTime = null;
        this.endTime = null;
    }

    public Event(String description, LocalDate start, LocalTime startTime, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
        this.startTime = startTime;
        this.endTime = null;
    }

    public Event(String description, LocalDate start, LocalDate end, LocalTime endTime) {
        super(description);
        this.start = start;
        this.end = end;
        this.startTime = null;
        this.endTime = endTime;
    }
    public Event(String description, LocalDate start, LocalTime startTime, LocalDate end, LocalTime endTime) {
        super(description);
        this.start = start;
        this.end = end;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return Desired string representation of the task.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[E] " + super.toString() + " (from: " + this.start.format(dateFormat)
                + this.startTimeString() + " to: "
                + this.end.format(dateFormat) + this.endTimeString()+ ")";
    }

    private String startTimeString() {
        return (this.startTime != null)
                ? " " + this.startTime.format(DateTimeFormatter.ofPattern("h:mma"))
                : "";
    }

    private String endTimeString() {
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
    public String toStringInFile() {
        return "[E] /" + super.toStringInFile() + " / " + this.start
                + this.startTimeStringInFile() + " / " + this.end
                + this.endTimeStringInFile();
    }

    private String startTimeStringInFile() {
        return this.startTime != null
                ? " / " + this.startTime.format(DateTimeFormatter.ofPattern("HH:mm"))
                : "";
    }

    private String endTimeStringInFile() {
        return this.endTime != null
                ? " / " + this.endTime.format(DateTimeFormatter.ofPattern("HH:mm"))
                : "";
    }
}
