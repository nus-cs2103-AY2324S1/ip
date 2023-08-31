package rua.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDate from, LocalDate to, Boolean marked) {
        super(description, marked);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getType() {
        return "E";
    }

    public String getFrom() {
        return this.from.format(DateTimeFormatter.ofPattern("MM dd yyyy"));
    }

    public String getTo() {
        return this.to.format(DateTimeFormatter.ofPattern("MM dd yyyy"));
    }

    @Override
    public Event mark() {
        return new Event(this.description, this.from, this.to, true);
    }

    @Override
    public Event unmark() {
        return new Event(this.description, this.from, this.to, false);
    }

    @Override
    public Boolean isHappeningOnThatDate(LocalDate date) {
        return (date.isEqual(from) || date.isAfter(from)) &&
                (date.isEqual(to) || date.isBefore(to));
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Event)) {
            return false;
        }
        Event c = (Event) o;

        // Compare the data members and return accordingly
        return c.description.equals(this.description) &&
                c.marked.equals(this.marked) &&
                c.from.isEqual(this.from) &&
                c.to.isEqual(this.to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                this.from.format(DateTimeFormatter.ofPattern("MM dd yyyy")) +
                " to: " + this.to.format(DateTimeFormatter.ofPattern("MM dd yyyy")) +
                ")";
    }
}
