package dre.task;

import dre.exception.DreException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private LocalDate fromDate;
    private LocalDate toDate;

    public Event(String task, LocalDate fromDate, LocalDate toDate) {
        super(task);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Provides a formatted string for saving this event task to a file.
     *
     * @return A formatted string suitable for file storage.
     */
    @Override
    public String fileSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return "E|" + super.fileSaveFormat() + "|" +
                fromDate.format(formatter) + "|" + toDate.format(formatter);
    }

    /**
     * Converts the event task to a string format for display.
     *
     * @return A formatted string representing this task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + formatDate(fromDate) + " to: " + formatDate(toDate) + ")";
    }

    /**
     * Formats the date for display purposes.
     *
     * @param date The date to be formatted.
     * @return A string representing the formatted date.
     */
    private String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(formatter);
    }

    public void editFromDate(String newFromDate) throws DreException {
        LocalDate newFrom = LocalDate.parse(newFromDate);
        if (newFrom.isAfter(this.toDate)) {
            throw new DreException("The 'from date' cannot be after the 'to date'.");
        }
        this.fromDate = newFrom;
    }

    public void editToDate(String newToDate) throws DreException {
        LocalDate newTo = LocalDate.parse(newToDate);
        if (newTo.isBefore(this.fromDate)) {
            throw new DreException("The 'to date' cannot be before the 'from date'.");
        }
        this.toDate = newTo;
    }
}