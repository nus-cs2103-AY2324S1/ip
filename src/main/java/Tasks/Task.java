package Tasks;

import Exceptions.DukeException;
import Exceptions.InvalidDateFormatException;
import Exceptions.InvalidDateTimeFormatException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Represents a task the user has created.
 */
public abstract class Task {
    // TODO: Make this class abstract?
    protected String description;
    protected boolean isDone = false;
    public static final String DATE_FORMAT ="d/M/yyyy";
    public static final String DATETIME_FORMAT = "d/M/yyyy h:m a";

    public Task(String description) {
        this.description = description;
    }

    /**
     * Retrieves the status of the Task.
     * An "X" represents that the Task is done.
     *
     * @return The status of the Task as a String.
     */
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Marks the Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the Task as done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns the String representation of the Task to be displayed.
     * It contains the status and description of the Task.
     *
     * @return The String to be displayed.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Returns the String representation of the Task to be saved in a data file.
     * It contains the status and description of the Task with "|" as a divider.
     *
     * @return The String to be saved as a line in the file.
     */
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
