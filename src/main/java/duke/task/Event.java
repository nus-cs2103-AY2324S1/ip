package duke.task;

import duke.parser.TimeParser;

import java.time.LocalDateTime;

/**
 * Represents an Event Task.
 * @author Toh Li Yuan (A0255811H)
 */
public class Event extends Task {
    private LocalDateTime startDate, endDate;

    /**
     * Creates a new Event Task.
     *
     * @param name the name of the Event.
     * @param startDate the starting date of the Event.
     * @param endDate the ending date of the Event.
     */
    public Event(String name, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.status = false;
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
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = TaskTypes.EVENT;
    }

    @Override
    public String toString() {
        String statusMark = this.status ? "[✓]" : "[✕]";
        return String.format("[E]%s %s (from: %s to: %s)", statusMark, name, TimeParser.returnTime(startDate), TimeParser.returnTime(endDate));
    }

    @Override
    public String toSave() {
        return String.format("E%s%s%s%d%s%s to %s", DISCRIMINATOR, name, DISCRIMINATOR, Boolean.compare(status, false), DISCRIMINATOR, TimeParser.toSaveString(startDate), TimeParser.toSaveString(endDate));
    }
}
