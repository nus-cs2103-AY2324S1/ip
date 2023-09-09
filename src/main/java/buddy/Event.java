package buddy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end date.
 */
public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    /**
     * The constructor for an event task.
     * @param description The description of the event task
     * @param start The start date for the event task
     * @param end The end date for the event task
     * @param isDone The status of the event task
     */
    public Event(String description, LocalDate start, LocalDate end, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;

        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // this.start = LocalDate.parse(start, formatter);
        // this.end = LocalDate.parse(end, formatter);
    }

    private String getDateString(LocalDate date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toSaveFileFormat() {
        return String.format("%s | %d | %s | %s | %s",
                getTaskType(),
                isDone ? 1 : 0,
                this.description,
                this.getDateString(start, "yyyy-MM-dd"),
                this.getDateString(end, "yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getDateString(start, "yyyy-MM-dd")
                + " to: " + getDateString(end, "yyyy-MM-dd") + ")";
    }
}
