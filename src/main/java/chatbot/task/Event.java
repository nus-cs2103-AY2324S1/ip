package chatbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class that inherits from Task.
 * 
 * @var from Representing start time
 * @var to Representing end time
 * 
 * @author Owen Yeo
 */
public class Event extends Task{
    private LocalDateTime from;
    private LocalDateTime to;
    private String fromString;
    private String toString;

    /**
     * Constructor for an event object.
     * 
     * @param label Descriptor for the event
     * @param from Start time
     * @param to End time
     */
    public Event(String label, String from, String to) {
        super(label);
        this.from = LocalDateTime.parse(from, DateTimeFormatter
            .ofPattern("yyyy-MM-dd HHmm"));
        this.fromString = from;
        this.to = LocalDateTime.parse(to, DateTimeFormatter
            .ofPattern("yyyy-MM-dd HHmm"));
        this.toString = to;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toSaveString() {
        return "E " + super.toSaveString() + " | " + fromString + " | " +
            toString;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from
            .format(DateTimeFormatter.ofPattern("MMM dd YYYY ha")) + " | to: " + to
            .format(DateTimeFormatter.ofPattern("MMM dd YYYY ha")) + ")";
    }
}
