package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a task with a specific deadline, which can be either a date and time or just a date.
 * It extends the Task class and adds a 'by' field to store the deadline.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a Deadline task with a description and a deadline in the form of a LocalDateTime.
     *
     * @param description The description of the Deadline task.
     * @param dateTime    The LocalDateTime representing the deadline date and time.
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description, dateTime);
    }

    /**
     * Constructs a Deadline task with a description and a deadline in the form of a LocalDate.
     *
     * @param description The description of the Deadline task.
     * @param date        The LocalDate representing the deadline date.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.by = date;
    }

    /**
     * Returns a string representation of the Deadline task, including its status icon, description, and deadline.
     * The date and time format used is "MMM dd yyyy HH:mm" (e.g., "Jan 01 2023 14:30") for LocalDateTime
     * and "MMM dd yyyy" (e.g., "Jan 01 2023") for LocalDate, with the English locale.
     *
     * @return A formatted string representing the Deadline task.
     */
    @Override
    public String toString() {
        String status = "[" + getStatusIcon() + "] ";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH);
        return " " + "[D]" + status + description + " (by: " + dateTime.format(formatter) + ")";
    }
}
