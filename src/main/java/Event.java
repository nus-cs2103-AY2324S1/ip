import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private final LocalDate from;
    private final LocalDate to;

    Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    Event(String description, LocalDate from, LocalDate to, Boolean marked) {
        super(description, marked);
        this.from = from;
        this.to = to;
    }

    @Override
    Event mark() {
        return new Event(this.description, this.from, this.to, true);
    }

    @Override
    Event unmark() {
        return new Event(this.description, this.from, this.to, false);
    }

    @Override
    Boolean happenOnThatDate(LocalDate date) {
        return (date.isEqual(from) || date.isAfter(from)) &&
                (date.isEqual(to) || date.isBefore(to));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                ")";
    }
}
