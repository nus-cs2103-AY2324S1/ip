package duke.tasks;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;


    public Event(String description, LocalDateTime from, LocalDateTime to, boolean marked) {
        super(description, "event", marked);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getOriginalMessage() {
        return "event " + this.getDescription() + " /from " + this.stringifyDate(this.from) + " /to " + this.stringifyDate(this.to);
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.formatDate(this.from) + " to: " + this.formatDate(this.to) + ")" ;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;

        if (o instanceof Event) {
            Event d = (Event) o;
            if (!this.from.equals(d.from)) return false;

            return this.to.equals(d.to);
        }

        return false;
    }
}
