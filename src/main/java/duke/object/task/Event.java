package duke.object.task;

import duke.command.task.EventCommand;
import duke.exception.DateRangeException;
import duke.ui.Ui;
import java.time.LocalDate;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

/**
 * Task with a start and end date.
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor for Event.
     * 
     * @param description The user's description of the task.
     * @param from The start date of the task.
     * @param to The end date of the task.
     * @throws DateRangeException When the start date is after the end date.
     */
    public Event(String description, LocalDate from, LocalDate to) throws DateRangeException {
        super(description);
        if (to.isBefore(from)) {
            throw new DateRangeException();
        }
        this.from = from;
        this.to = to;
    }

    /**
     * @inheritdoc
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                Ui.stringifyDate(this.from), Ui.stringifyDate(this.to));
    }

    /**
     * @inheritdoc
     */
    @Override
    public String toCommand(int idx) {
        return (new EventCommand(Map.ofEntries(
                new SimpleEntry<>("description", this.description),
                new SimpleEntry<>("from", this.from),
                new SimpleEntry<>("to", this.to)))).toString()
                + "\n" + super.toCommand(idx) + "\n";
    }

    /**
     * Returns whether the event is happening on a given date.
     * 
     * @param date The date one is checking.
     * @return Whether the event is happening on that date.
     */
    public boolean isOngoing(LocalDate date) {
        return !(date.isBefore(this.from) || date.isAfter(this.to));
    }

}
