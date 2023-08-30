package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end date/time.
 */
public class Event extends Task {
    private String from;
    private String to;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    /**
     * Constructs an Event task with the given description and date/time details.
     *
     * @param description The description of the event.
     * @param from        The start date/time of the event.
     * @param to          The end date/time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        this.fromDate = LocalDateTime.parse(this.from, inputFormatter);
        this.toDate = LocalDateTime.parse(this.to, inputFormatter);
    }


    /**
     * Retrieves the formatted start date/time of the event.
     *
     * @return The formatted start date/time in "yyyy-MM-dd HHmm" pattern.
     */
    public String getFrom() {
        return this.fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Retrieves the formatted end date/time of the event.
     *
     * @return The formatted end date/time in "yyyy-MM-dd HHmm" pattern.
     */
    public String getTo() {
        return this.toDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Generates a formatted string representation of the event.
     *
     * @return A string displaying the event details, including dates/times.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma"))
                        + " to: " + this.toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")) + ")";
    }
}
