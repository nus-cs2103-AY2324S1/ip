package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

/**
 * Represents an event task, which extends the base `Task` class.
 */
public class EventTask extends Task {
    protected LocalDate fromDate;
    protected LocalDate toDate;

    /**
     * Constructs an `EventTask` object with a description, start date, end date, and completion status.
     *
     * @param description The description of the task.
     * @param from        The start date of the event in the "YYYY-MM-DD" format.
     * @param to          The end date of the event in the "YYYY-MM-DD" format.
     * @param isDone      The completion status of the task.
     * @throws DukeException     If the input date format is invalid.
     */
    public EventTask(String description, String from, String to, boolean isDone) throws DukeException {
        super(description, isDone);
        try {
            setEventFromDate(from);
            setEventToDate(to);
        } catch (DateTimeException e) {
            throw new DukeException("Please input your date in the YYYY-MM-DD format!");
        }
    }

    /**
     * Sets the start date of the event task.
     *
     * @param date The start date in the "YYYY-MM-DD" format.
     */
    public void setEventFromDate(String date) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.fromDate = LocalDate.parse(date, formatter);
        } catch (DateTimeException e) {
            throw new DukeException("There is no such date!");
        }
    }
    /**
     * Sets the end date of the event task.
     *
     * @param date The end date in the "YYYY-MM-DD" format.
     */
    public void setEventToDate(String date) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.toDate = LocalDate.parse(date, formatter);
        } catch (DateTimeException e) {
            throw new DukeException("There is no such date!");
        }
    }

    /**
     * Gets the formatted start date of the event task.
     *
     * @return The formatted start date in "MMM dd yyyy" format.
     */
    public String getFormattedFromDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return fromDate.format(formatter);
    }

    /**
     * Gets the formatted end date of the event task.
     *
     * @return The formatted end date in "MMM dd yyyy" format.
     */
    public String getFormattedToDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return toDate.format(formatter);
    }

    /**
     * Converts the task to a string for saving to the data file.
     *
     * @return A string representation of the task for saving.
     */
    public String toSave() {
        return "[E]" + super.toString() + " (from: " + this.fromDate + " to: " + this.toDate + ")";
    }

    /**
     * Gets the end date of the event task.
     *
     * @return The end date as a `LocalDate` object.
     */
    public LocalDate getToDate() {
        return toDate;
    }

    /**
     * Gets the start date of the event task.
     *
     * @return The start date as a `LocalDate` object.
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * Returns a string representation of the `EventTask`.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFormattedFromDate() + " to: " + getFormattedToDate() + ")";
    }
}
