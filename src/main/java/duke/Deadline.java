package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Locale;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    // protected String by;
    private LocalDate deadline;

    /**
     * Constructs a Deadline task.
     *
     * @param description   The description of the task.
     * @param deadlineString  The deadline date string in "yyyy-MM-dd format.
     */
    public Deadline (String description, String deadlineString) {
        super(description);
        this.deadline = parseDeadline(deadlineString);
    }

    /**
     * Parses the deadline date from string.
     *
     * @param deadlineString  The deadline date string in "yyyy-MM-dd" format.
     * @return The parsed deadline date as a LocalDate object, or null if parsing fails.
     */
    private LocalDate parseDeadline (String deadlineString) {
        try {
            if (!deadlineString.isEmpty()) {
                return LocalDate.parse(deadlineString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date: " + deadlineString);
        }
        return null;
    }

    private String formatDeadline (LocalDate deadlineDate) {
        if (deadlineDate == null) {
            return "Invalid date.";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
        return deadlineDate.format(formatter);
    }

    private String saveFormatDeadline (LocalDate deadlineDate) {
        if (deadlineDate == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return deadlineDate.format(formatter);
    }

    /**
     * Returns a string representation of the task for saving.
     *
     * @return  The formatted string representation of the task.
     */
    @Override
    public String toDataString() {
        return "DEADLINE | " + super.toDataString() + " | " + saveFormatDeadline(deadline);
    }

    /**
     * Returns a string representation of the task for display.
     *
     * @return The formatted string representation fo the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDeadline(deadline) + ")";
    }
}
