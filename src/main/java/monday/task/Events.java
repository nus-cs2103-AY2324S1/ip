package monday.task;

import java.time.LocalDateTime;

import monday.monday.dateTime.DateFormatter;

/**
 * The Events class extends the Task class.
 * It contains a description, start time, end time, and inherits the
 * completion status functionality from the Task class.
 */
public class Events extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs an Events object with the given description, start time, and end time.
     *
     * @param description the description of the Events task
     * @param start the start time of the Events task
     * @param end the end time of the Events task
     */
    public Events(String description, String start, String end) {
        super(description);
        this.start = DateFormatter.parseTime(start, "yyyy-MM-dd HH:mm");;
        this.end = DateFormatter.parseTime(end, "yyyy-MM-dd HH:mm");;
    }

    /**
     * Returns a string representation of the Events task,
     * including its task type indicator, description, start time, and end time.
     *
     * @return a string representation of the Events task
     */
    @Override
    public String toString() {
        String formatStartDateTime = DateFormatter.format(start, "dd-MM-yyyy HH:mm");
        String formatEndDateTime = DateFormatter.format(end, "dd-MM-yyyy HH:mm");
        return "[E]" + super.toString() + " (from: " + formatStartDateTime + " to: " + formatEndDateTime + ")";
    }
}