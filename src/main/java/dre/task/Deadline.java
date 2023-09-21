package dre.task;

import dre.exception.DreException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private LocalDate byDate;

    /**
     * Creates a new deadline task.
     *
     * @param task The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String task, LocalDate by) {
        super(task);
        this.byDate = by;
    }

    /**
     * Provides a formatted string for saving this deadline task to a file.
     *
     * @return A formatted string suitable for file storage.
     */
    @Override
    public String fileSaveFormat() {
        return "D|" + super.fileSaveFormat() + "|" +
                byDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Converts the task to a string format for display.
     *
     * @return A formatted string representing this task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDate(byDate) + ")";
    }

    /**
     * Formats the date for display purposes.
     *
     * @param date The date to be formatted.
     * @return A string representing the formatted date in MMM dd yyyy format.
     */
    private String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(formatter);
    }

    /**
     * Modifies the due date of the deadline task.
     *
     * @param newByDate The new due date to be set for the task in the format yyyy-MM-dd.
     * @throws DreException If the provided date format is invalid.
     */
    public void editByDate(String newByDate) throws DreException {
        try {
            LocalDate parsedDate = LocalDate.parse(newByDate);
            this.byDate = parsedDate;
        } catch (DateTimeParseException e) {
            throw new DreException("Please provide a valid date in the format yyyy-MM-dd.");
        }
    }
}