package dre.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private final LocalDate by;

    /**
     * Creates a new deadline task.
     *
     * @param task The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String task, LocalDate by) {
        super(task);
        this.by = by;
    }

    /**
     * Provides a formatted string for saving this deadline task to a file.
     *
     * @return A formatted string suitable for file storage.
     */
    @Override
    public String fileSaveFormat() {
        return "D|" + super.fileSaveFormat() + "|" + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Converts the task to a string format for display.
     *
     * @return A formatted string representing this task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDate(by) + ")";
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
}