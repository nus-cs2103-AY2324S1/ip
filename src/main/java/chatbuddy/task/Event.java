package chatbuddy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Event represents an Event object in ChatBuddy.
 * An event is a task that has a 'from' datetime and a 'to' datetime.
 */
public class Event extends Task {

    /** The 'from' datetime of the task. */
    protected LocalDateTime from;
    /** The 'to' datetime of the task. */
    protected LocalDateTime to;

    /** The formatter used for datetime inputs. */
    private static final DateTimeFormatter FORMATTER_DATETIME_INPUT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    /** The formatter used for datetime outputs. */
    private static final DateTimeFormatter FORMATTER_DATETIME_OUTPUT = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");

    /**
     * Constructor to create an chatbuddy.task.Event object.
     *
     * @param description The task description.
     * @param from The task's start date/time in the format dd/MM/yyyy HHmm.
     * @param to The task's end date/time in the format.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a boolean representing whether the event starts within a week.
     *
     * @return True if the event starts within a week, false otherwise.
     */
    @Override
    public boolean isWithinAWeek() {
        return from.isBefore(LocalDateTime.now().plus(1, ChronoUnit.WEEKS));
    }

    @Override
    public String toString() {
        return String.format(
                "[E]%1s (from: %2s to: %3s)",
                super.toString(),
                from.format(FORMATTER_DATETIME_OUTPUT),
                to.format(FORMATTER_DATETIME_OUTPUT)
        );
    }

    /**
     * Returns deadline task information in format for saving.
     * The format is E | [1 if completed, 0 if not completed] | [task description] | [from] | [to].
     *
     * @return The event task information in format for saving.
     */
    @Override
    public String getInformationForSaving() {
        return String.format(
                "E | %1s | %2s | %3s",
                super.getInformationForSaving(),
                from.format(FORMATTER_DATETIME_INPUT),
                to.format(FORMATTER_DATETIME_INPUT));
    }
}
