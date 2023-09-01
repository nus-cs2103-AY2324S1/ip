import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Event task in Richie application
 */
class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructor for the Event task
     * @param description Description of the task
     * @param from The time that the event begins
     * @param to The time that the event ends
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + convertFromToString() + " to: " + convertToToString() + ")";
    }

    /**
     * Returns LocalDateTime date and time of when task starts
     * @return LocalDateTime of when the task starts
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     * Returns LocalDateTime date and time of when task is done
     * @return LocalDateTime of when the task ends
     */
    public LocalDateTime getTo() {
        return this.to;
    }

    /**
     * Converts From to a String format
     * @return String that represents the From
     */
    public String convertFromToString() {
        return this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy 'at' hh:mm a"));
    }

    /**
     * Converts To to a string format
     * @return String that represents the To
     */
    public String convertToToString() {
        return this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy 'at' hh:mm a"));
    }
}
