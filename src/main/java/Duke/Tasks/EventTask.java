package Duke.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a specific task of type Event.
 *
 * Contains a start time, and an end time.
 */
public class EventTask extends Task {

    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;

    /**
     * Constructor for an Event Task
     *
     * @param itemName The name of the task
     * @param fromDateTime The time the event starts
     * @param toDateTime The time the event ends
     */
    public EventTask(String itemName, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(itemName);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }


    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String encodeTask() {
        return super.encodeTask() + " | " + this.fromDateTime + " | " + this.toDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + this.fromDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) +
                " to: " + this.toDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
    }
}
