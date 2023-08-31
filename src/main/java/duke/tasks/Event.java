package duke.tasks;

import duke.components.Parser;

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
    public Event(String name, LocalDateTime startTime, LocalDateTime endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                startTime.format(Parser.DATETIME_FORMATTER), endTime.format(
                        Parser.DATETIME_FORMATTER));
    }

    @Override
    public String generateSaveString() {
        return String.format("E | %b | %s  /from %s /to %s", isDone, name, startTime.format(
                Parser.DATETIME_FORMATTER),
                endTime.format(Parser.DATETIME_FORMATTER));
    }

    @Override
    public boolean isOccurringOnDate(LocalDate date) {
        return (!date.isBefore(startTime.toLocalDate()))
                && (!date.isAfter(endTime.toLocalDate()));
    }
}
