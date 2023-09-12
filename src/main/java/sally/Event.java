package sally;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with start and end date/time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event object with the specified task description, start date/time, and end date/time.
     *
     * @param task The description of the event.
     * @param from The start date/time of the event.
     * @param to The end date/time of the event.
     */
    public Event(String task, LocalDateTime from, LocalDateTime to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event object with the specified task description, start date/time, end date/time, and priority.
     *
     * @param task The description of the event.
     * @param from The start date/time of the event.
     * @param to The end date/time of the event.
     * @param priority The priority of the event.
     */
    public Event(String task, LocalDateTime from, LocalDateTime to, String priority) {
        super(task, priority);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts the Event object to its string representation.
     *
     * @return The string representation of the Event object.
     */
    @Override
    public String toString() {
        String startDate;
        String endDate;

        DateTimeFormatter outputFormatterFrom =
                DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        startDate = from.format(outputFormatterFrom);

        DateTimeFormatter outputFormatterTo =
                DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        endDate = to.format(outputFormatterTo);

        return "[E]" + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }
}
