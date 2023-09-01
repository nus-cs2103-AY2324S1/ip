package noac.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event task which contains an addition from and to date compared to task.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for the Event.
     *
     * @param description Description on what the task is.
     * @param from When the task starts.
     * @param to When the task is due.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts the task to string.
     *
     * @return The string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))  + ")";
    }

    /**
     * Converts to string to save to file.
     *
     * @return The string to be saved.
     */
    @Override
    public String printToFile() {
        return "E|" + super.printToFile() + "|"
                + this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + "|"
                + this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Getter function for the from.
     *
     * @return the LocalDateTime when the task starts.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Getter function for the to.
     *
     * @return the LocalDateTime when the task is due.
     */
    public LocalDateTime getTo() {
        return to;
    }
}
