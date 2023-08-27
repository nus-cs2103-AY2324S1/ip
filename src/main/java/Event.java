import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    /**
     * The start time of the event.
     */
    protected LocalDateTime from;
    /**
     * The end time of the event.
     */
    protected LocalDateTime to;
    /**
     * The type of the task.
     */
    /**
     * Constructs an Event object.
     *
     * @param description The description of the task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */

    public Event (String description, String from, String to) {
        super(description, "E");
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String toFileString() {
        return this.type + " | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + this.from + " | " + this.to;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String formattedFrom = from.format(formatter);
        String formattedTo = to.format(formatter);
        return "[" + this.type + "]" + super.toString() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }
}
