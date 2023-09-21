package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Encapsulates events which are Tasks with start and end.
 */
public class Event extends Task {
    private LocalDate startTime;
    private LocalDate endTime;

    /**
     * Constructs an Event with specified description, start time, end time,
     * and whether it is done.
     */
    public Event(String description, LocalDate startTime, LocalDate endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructs an Event with specified description, start time and end time.
     */
    public Event(String description, boolean isDone, LocalDate startTime, LocalDate endTime) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a String representation for the task for output.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + startTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH))
                + " to: "
                + endTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH)) + ")";
    }

    /**
     * Returns a String representation of the task for storage.
     */
    @Override
    public String toTxt() {
        return "E | " + super.toTxt() + " | "
                + this.startTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH))
                + " | " + this.endTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH));
    }
}
