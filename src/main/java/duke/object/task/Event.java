package duke.object.task;

import duke.command.task.EventCommand;
import duke.exception.DateRangeException;
import duke.ui.Ui;
import java.time.LocalDate;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) throws DateRangeException {
        super(description);
        if (to.isBefore(from)) {
            throw new DateRangeException();
        }
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), Ui.stringifyDate(this.from), Ui.stringifyDate(this.to));
    }

    @Override
    public String toCommand(int idx) {
        return (new EventCommand(Map.ofEntries(new SimpleEntry<>("description", this.description), new SimpleEntry<>("from", this.from), new SimpleEntry<>("to", this.to)))).toString()
                + "\n" + super.toCommand(idx) + "\n";
    }

    public boolean isOngoing(LocalDate date) {
        return !(date.isBefore(this.from) || date.isAfter(this.to));
    }

}
