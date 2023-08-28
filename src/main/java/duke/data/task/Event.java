package duke.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    final DateTimeFormatter PARSE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    final DateTimeFormatter PRINT_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy");

    private LocalDate from;
    private LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFormattedFrom(DateTimeFormatter formatter) {
        return from.format(formatter);
    }

    public String getFormattedTo(DateTimeFormatter formatter) {
        return to.format(formatter);
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)",
                super.toString(), getFormattedFrom(PRINT_FORMATTER), getFormattedTo(PRINT_FORMATTER));
    }

    @Override
    public String getStorageString() {
        return "E | " + super.getStorageString() + " | "
                + getFormattedFrom(PARSE_FORMATTER) + " | " + getFormattedTo(PARSE_FORMATTER);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Event) {
            Event event = (Event) obj;

            return this.description.equals(event.description)
                    && this.from.equals(event.from)
                    && this.to.equals(event.to);
        }

        return false;
    }
}
