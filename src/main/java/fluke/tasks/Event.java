package fluke.tasks;

import fluke.exceptions.FlukeException;
import fluke.exceptions.InvalidInputException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Event extends Task {
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
    protected LocalDate fromDate;
    protected LocalDate toDate;
    public Event(String description, String fromDate, String toDate) throws FlukeException {
        super(description);
        try {
            this.fromDate = LocalDate.parse(fromDate);
            this.toDate = LocalDate.parse(toDate);
            if (this.fromDate.isAfter(this.toDate)) {
                throw new InvalidInputException();
            }
        } catch (DateTimeParseException d) {
            throw new InvalidInputException();
        }

    }

    public Event(String description, boolean isDone, String fromDate, String toDate) throws FlukeException {
        super(description, isDone);
        this.fromDate = LocalDate.parse(fromDate);
        this.toDate = LocalDate.parse(toDate);
    }

    @Override
    public String toString() {
        String fromString = fromDate.format(DATE_TIME_FORMATTER);
        String toString = toDate.format(DATE_TIME_FORMATTER);
        return "[E]" + super.toString() + " (from: " + fromString + " to: " + toString + ")";
    }
}
