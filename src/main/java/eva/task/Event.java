package eva.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An Event class to represent event tasks
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs deadline with the given description
     *
     * @param description the given description of the deadline
     * @param isDone      whether the event is done
     * @param from        the time at which the event starts
     * @param to          the time at which the event ends
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone, TaskType.EVENT);
        this.from = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.to = LocalDate.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns the do by date
     *
     * @return String representing start from date
     */
    public String getFrom() {
        return from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns the do by date
     *
     * @return String representing end at date
     */
    public String getTo() {
        return to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns String representing the event
     *
     * @return String representing event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}