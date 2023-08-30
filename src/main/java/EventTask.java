import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates an event with a start and end time/date.
 * Inherits from Task class.
 * @author Wu Jingya
 */
public class EventTask extends Task{
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Creates an Event Task with the specified description and start and end time/date
     * @param description The task's description
     * @param from The task's starting time/date
     * @param to The task's ending time/date
     */
    public EventTask(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public EventTask(String description, LocalDateTime from, LocalDateTime to, Boolean done) {
        super(description, done);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the task
     * @return The string representation of the task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + " " +
                "to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }

    @Override
    public String toData() {
        return "E|" + super.toData() + "|" + from + "|" + to;
    }
}
