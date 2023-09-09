package dre.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    public Event(String task, LocalDate from, LocalDate to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    /**
     * Provides a formatted string for saving this event task to a file.
     *
     * @return A formatted string suitable for file storage.
     */
    @Override
    public String fileSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return "E|" + super.fileSaveFormat() + "|" + from.format(formatter) + "|" + to.format(formatter);
    }

    /**
     * Converts the event task to a string format for display.
     *
     * @return A formatted string representing this task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDate(from) + " to: " + formatDate(to) + ")";
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
}