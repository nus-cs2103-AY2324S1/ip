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
        String formattedFrom = this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
        String formattedTo = this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));

        return "[E]" + super.toString() + " (from: "
                + formattedFrom + " to: "
                + formattedTo + ") "
                + tagsToString();
    }

    /**
     * Converts to string to save to file.
     *
     * @return The string to be saved.
     */
    @Override
    public String printToFile() {
        String formattedFrom = this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        String formattedTo = this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

        return "E|" + super.printToFile() + "|"
                + formattedFrom + "|"
                + formattedTo + "|"
                + tagsToString();
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
