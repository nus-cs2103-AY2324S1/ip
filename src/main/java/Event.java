import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Event is a task with a start time and end time
 */
public class Event extends Task{
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    /**
     * Constructs a new Event object
     * @param name Name of event
     * @param startTime Start time of event
     * @param endTime End time of event
     */
    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = LocalDateTime.parse(startTime, DATETIME_FORMAT);
        this.endTime = LocalDateTime.parse(endTime, DATETIME_FORMAT);
    }

    /**
     * String representation of Event
     * @return String representation of Event
     */
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                startTime.format(DATETIME_FORMAT), endTime.format(DATETIME_FORMAT));
    }

    @Override
    public String generateSaveString() {
        return String.format("E | %b | %s  /from %s /to %s", isDone, name, startTime.format(DATETIME_FORMAT),
                endTime.format(DATETIME_FORMAT));
    }

    @Override
    public boolean isOccurringOnDate(LocalDate date) {
        return (!date.isBefore(startTime.toLocalDate())) && (!date.isAfter(endTime.toLocalDate()));
    }
}
