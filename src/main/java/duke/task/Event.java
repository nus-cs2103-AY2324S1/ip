package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import duke.parser.TimeParser;

/**
 * Represents an Event Task.
 * @author Toh Li Yuan (A0255811H)
 */
public class Event extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Creates a new Event Task.
     *
     * @param name the name of the Event.
     * @param startDate the starting date of the Event.
     * @param endDate the ending date of the Event.
     */
    public Event(String name, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.isDone = false;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = TaskTypes.EVENT;
    }

    /**
     * Creates a new Event Task.
     *
     * @param name the name of the Event.
     * @param status the completion status of the Event.
     * @param startDate the starting date of the Event.
     * @param endDate the ending date of the Event.
     */
    public Event(String name, boolean status, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.isDone = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = TaskTypes.EVENT;
    }

    @Override
    public String toString() {
        String statusMark = this.isDone ? "[✓]" : "[✕]";
        return String.format("[E]%s %s (from: %s to: %s)", statusMark, name,
                TimeParser.returnTime(startDate), TimeParser.returnTime(endDate));
    }

    @Override
    public String toSave() {
        return String.format("E%s%s%s%d%s%s to %s", DISCRIMINATOR, name, DISCRIMINATOR,
                Boolean.compare(isDone, false), DISCRIMINATOR, TimeParser.toSaveString(startDate),
                TimeParser.toSaveString(endDate));
    }

    @Override
    public String getReminder(LocalDate currDate, int days) {
        if (this.isDone) {
            return null;
        }

        if (this.endDate.getYear() != currDate.getYear()) {
            return null;
        }
        if (currDate.getDayOfYear() + days - this.endDate.getDayOfYear() >= 0) {
            return this.toString();
        }
        return null;
    }
}
