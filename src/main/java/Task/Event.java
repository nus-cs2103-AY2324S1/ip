package task;
import java.time.LocalDateTime;

/**
 * The `Event` class represents an event task in the BloopBot application.
 * It extends the base `Task` class and inherits its properties and methods.
 * An `Event` task includes a description and a time period represented by "from" and "to" strings.
 * It can be marked as completed or not completed.
 * This class provides methods to create and format `Event` tasks.
 *
 * @author raydenlim
 * @version 0.0.0
 */
public class Event extends Task {
    protected String from;
    protected String to;
    protected LocalDateTime fromDate;
    protected LocalDateTime toDate;

    /**
     * Constructs a new `Event` task with the specified description and time period.
     *
     * @param description The description of the `Event` task.
     * @param from        The starting date and time of the event.
     * @param to          The ending date and time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.fromDate = parseDateTime(from);
        this.to = to;
        this.toDate = parseDateTime(to);
    }

    /**
     * Returns a string representation of the `Event` task.
     * The representation includes the task's type icon, status icon, description,
     * and the time period in a human-readable format.
     *
     * @return The string representation of the `Event` task.
     */
    @Override
    public String toString() {
        if (fromDate != null && toDate != null) {
            return getTask() + getStatusIcon() + " " + description + " (from: " + super.printDateTime(this.fromDate)
                    + " to: " + super.printDateTime(this.toDate) + ")";
        } else {
            return getTask() + getStatusIcon() + " " + description + " (from: " + from + " to: " + to + ")";
        }
    }
}
