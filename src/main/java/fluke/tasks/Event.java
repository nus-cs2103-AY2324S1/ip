package fluke.tasks;

import fluke.exceptions.FlukeException;
import fluke.exceptions.InvalidInputException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Event extends Task {
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
    protected LocalDate from;
    protected LocalDate to;
    public Event(String description, String from, String to) throws FlukeException {
        super(description);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
            if (this.from.isAfter(this.to)) {
                throw new InvalidInputException();
            }
        } catch (DateTimeParseException d) {
            throw new InvalidInputException();
        }

    }

    public Event(String description, boolean isDone, String from, String to) throws FlukeException {
        super(description, isDone);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String toString() {
        String fromString = from.format(DATE_TIME_FORMATTER);
        String toString = to.format(DATE_TIME_FORMATTER);
        return "[E]" + super.toString() + " (from: " + fromString + " to: " + toString + ")";
    }
}
