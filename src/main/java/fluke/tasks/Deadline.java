package fluke.tasks;

import fluke.exceptions.FlukeException;
import fluke.exceptions.InvalidInputException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;


public class Deadline extends Task {
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
    protected LocalDate byDate;
    public Deadline(String description, String byDate) throws FlukeException {
        super(description);
        try {
            this.byDate = LocalDate.parse(byDate);
        } catch (DateTimeParseException d) {
            throw new InvalidInputException();
        }
    }

    public Deadline(String description, boolean isDone, String byDate) throws FlukeException {
        super(description, isDone);
        this.byDate = LocalDate.parse(byDate);
    }

    @Override
    public String toString() {
        String byString = byDate.format(DATE_TIME_FORMATTER);
        return "[D]" + super.toString() + " (by: " + byString + ")";
    }
}
