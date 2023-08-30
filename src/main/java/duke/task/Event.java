package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import duke.Keyword;
import duke.Storage;
import duke.Time;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String fileFormat() {
        return String.format("E%s%s%s%s%s%s",
                Storage.SEPARATOR, super.fileFormat(),
                Storage.SEPARATOR, this.from.format(Time.DATE_TIME_FORMATTER),
                Storage.SEPARATOR, this.to.format(Time.DATE_TIME_FORMATTER));
    }

    @Override
    public boolean onDate(Keyword key, LocalDate date) {
        LocalDate from = this.from.toLocalDate();
        LocalDate to = this.to.toLocalDate();
        return key.equals(Keyword.EVENT)
                && (from.isBefore(date) || from.equals(date))
                && (to.isAfter(date) || to.equals(date));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.from.format(Time.DATE_TIME_DISPLAY_FORMATTER),
                this.to.format(Time.DATE_TIME_DISPLAY_FORMATTER));
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Event) {
            Event event = (Event) o;
            return super.equals(event) && this.from.equals(event.from) && this.to.equals(event.to);
        }
        return false;
    }
}
