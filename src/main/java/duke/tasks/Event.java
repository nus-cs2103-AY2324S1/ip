package duke.tasks;

import duke.Duke;

import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public String getTextRepresentation() {
        return "E | " + super.getTextRepresentation() + " | " + this.from.format(Duke.TIME_FORMAT) + " | "
                + this.to.format(Duke.TIME_FORMAT);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(Duke.TIME_FORMAT)
                + " to: " + this.to.format(Duke.TIME_FORMAT) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Event)) {
            return false;
        } else {
            Event event = (Event) o;
            return event.from.equals(this.from)
                    && event.to.equals(this.to)
                    && event.description.equals(this.description)
                    && event.isDone == this.isDone;
        }
    }
}
