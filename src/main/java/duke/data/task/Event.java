package duke.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event that has a start and end date.
 */
public class Event extends Task {

    private final DateTimeFormatter PARSE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter PRINT_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy");

    private LocalDate from;
    private LocalDate to;

    /**
     * Returns an instance of {@code Event} with the given description,
     * start date and end date.
     *
     * @param description The description of the event.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a String representation of the start date of the event.
     * @param formatter The specified format of the date.
     * @return The String representation of the start date of the event.
     */
    public String getFormattedFrom(DateTimeFormatter formatter) {
        return from.format(formatter);
    }

    /**
     * Returns a String representation of the end date of the event.
     * @param formatter The specified format of the date.
     * @return The String representation of the end date of the event.
     */
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
