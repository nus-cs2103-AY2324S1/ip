package Tasks;

import Exceptions.DukeException;
import Exceptions.InvalidDateFormatException;
import Exceptions.InvalidDateTimeFormatException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task {
    // TODO: Make this class abstract?
    protected String description;
    protected boolean isDone = false;

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

    /**
     * Checks if the description contains the keyword.
     * The check is non-case-sensitive.
     *
     * @param keyword The keyword that the user has entered.
     * @return Returns true if the description contains the keyword.
     */
    public boolean containsKeyword(String keyword) {
        return this.description.toLowerCase().contains(keyword.toLowerCase());
    }

    public LocalDateTime parseDateTime(String dateTimeString) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy h:m a");
        try {
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException();
        }
    }

    public LocalDate parseDate(String dateString) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        try {
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException(); // Rethrow the exception if needed for higher-level error handling
        }
    }
}
