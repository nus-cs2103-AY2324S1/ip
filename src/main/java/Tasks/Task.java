package Tasks;

import Exceptions.DukeException;
import Exceptions.InvalidDateFormatException;
import Exceptions.InvalidDateTimeFormatException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Task {
    // TODO: Make this class abstract?
    protected String description;
    protected boolean isDone = false;
    public static final String DATE_FORMAT ="d/M/yyyy";
    public static final String DATETIME_FORMAT = "d/M/yyyy h:m a";

    public Task(String description) {
        this.description = description;
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    public String toFileString() {
        return isDone
                ? "X | " + description
                : "0 | " + description;
    }

    public LocalDateTime parseDateTime(String dateTimeString) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
        try {
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException();
        }
    }

    public LocalDate parseDate(String dateString) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        try {
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException(); // Rethrow the exception if needed for higher-level error handling
        }
    }
}
