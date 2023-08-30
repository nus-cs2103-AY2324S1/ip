package duke.data.task;

import duke.data.exception.DukeException;
import duke.data.exception.DukeExceptionType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        setFrom(from);
        setTo(to);
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    @Override
    public String toSaveDataFormat() {
        return String.format("E | %d | %s | %s | %s", isDone() ? 1 : 0, getDescription(),
                getFrom().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")),
                getTo().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                getFrom().format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm")),
                getTo().format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm")));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Event) {
            Event otherEvent = (Event) other;
            return (this.description.equals(otherEvent.description))
                    && (this.from.equals(otherEvent.from))
                    && (this.to.equals(otherEvent.to));
        } else {
            return false;
        }
    }
}
