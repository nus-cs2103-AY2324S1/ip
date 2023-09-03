package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * A Event type task that can be handled by the chatbot.
 */
public class Event extends Task {
    /**
     * Date and time that the event starts.
     */
    private LocalDateTime from;

    /**
     * Date and time that the event ends.
     */
    private LocalDateTime to;

    /**
     * Constructor for the Deadline class.
     *
     * @param name Name of the Deadline.
     * @param from DateTime that the event starts.
     * @param from DateTime that the event ends.
     */
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event.
     *
     * @return A string representing the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(DATE_TIME_OUTPUT_FORMATTER)
                + " to: " + this.to.format(DATE_TIME_OUTPUT_FORMATTER) + ")";
    }

    /**
     * Returns a string representation of the event.
     *
     * @return A string representing the event to be saved.
     */
    @Override
    public String toSaveStateString() {
        String[] state = new String[]{ "event", this.getDone() ? "1" : "0", this.getTaskName(),
                this.from.format(Duke.DATE_TIME_INPUT_FORMATTER), this.to.format(Duke.DATE_TIME_INPUT_FORMATTER) };
        return String.join(" / ", state);
    }

    @Override
    public boolean isOnDate(LocalDate date) {
        return date.isEqual(this.from.toLocalDate()) || date.isEqual(this.to.toLocalDate())
                || (date.isAfter(this.from.toLocalDate()) && date.isBefore(this.to.toLocalDate()));
    }
}
