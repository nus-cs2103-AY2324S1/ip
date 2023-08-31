package harvard;
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

    public Event (String description, String from, String to) throws DukeException {
        super(description, "E");
        try {
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (Exception e) {
            System.out.println(e);
            throw new DukeException("Please enter a valid date and time in the format d/M/yyyy HHmm");
        }
    }
    /**
     * Constructs an Event object.
     *
     * @param description The description of the task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */

    public Event (String description, LocalDateTime from, LocalDateTime to) {
        super(description, "E");
        this.from = from;
        this.to = to;
    }
    /**
     * Returns the string representation of the task to be written to file.
     *
     * @return The string representation of the task to be written to file.
     */

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
