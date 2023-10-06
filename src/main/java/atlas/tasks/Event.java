package atlas.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

import atlas.components.Parser;
import atlas.exceptions.WrongDateTimeOrderException;

/**
 * Event is a task with a start time and end time
 */
public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Constructs a new Event object
     * @param name Name of event
     * @param startTime Start time of event
     * @param endTime End time of event
     * @throws WrongDateTimeOrderException Thrown if start time is after end time
     */
    public Event(String name, LocalDateTime startTime, LocalDateTime endTime) throws WrongDateTimeOrderException {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
        if (startTime.isAfter(endTime)) {
            throw new WrongDateTimeOrderException(startTime, endTime);
        }
    }

    /**
     * Constructs a new Event object with reminders
     * @param name Name of event
     * @param startTime Start time of event
     * @param endTime End time of event
     * @param reminderStartDate Date starting from which reminders should be sent
     * @throws WrongDateTimeOrderException Thrown if start time is after end time
     */
    public Event(String name, LocalDateTime startTime, LocalDateTime endTime,
                 LocalDate reminderStartDate) throws WrongDateTimeOrderException {
        super(name, reminderStartDate);
        if (startTime.isAfter(endTime)) {
            throw new WrongDateTimeOrderException(startTime, endTime);
        }
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
        if (hasReminder()) {
            assert reminderStartDate != null;
            return String.format("E | %b | %s /from %s /to %s /remind %s", isDone, name,
                    startTime.format(Parser.DATETIME_FORMATTER),
                    endTime.format(Parser.DATETIME_FORMATTER),
                    reminderStartDate.format(Parser.DATE_FORMATTER));
        }
        return String.format("E | %b | %s /from %s /to %s", isDone, name,
                startTime.format(Parser.DATETIME_FORMATTER),
                endTime.format(Parser.DATETIME_FORMATTER));
    }

    @Override
    public boolean isOccurringOnDate(LocalDate date) {
        return (!date.isBefore(startTime.toLocalDate()))
                && (!date.isAfter(endTime.toLocalDate()));
    }
}
