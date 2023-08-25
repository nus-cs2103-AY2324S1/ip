package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean isBefore(LocalDate date) {
        return this.to.isBefore(date);
    }

    @Override
    public String toString() {

        return "[E]" +
                super.toString() +
                " (from: " +
                from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +
                " to: " +
                to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +
                ")";
    }

    @Override
    public String getSaveString() {

        return String.format("%d event %s /from %s /to %s",isDone ? 1 : 0, description, from , to);
    }
}
