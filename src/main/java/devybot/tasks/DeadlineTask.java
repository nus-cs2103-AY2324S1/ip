package devybot.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The DeadlineTask class represents a task with a deadline in the DevyBot task
 * management system.
 */
public class DeadlineTask extends Task {
    protected LocalDateTime dateTime;
    protected LocalDate date;

    /**
     * Constructs a DeadlineTask with a description and a specific date and time.
     *
     * @param description The description of the task.
     * @param dateTime    The date and time of the deadline.
     */
    public DeadlineTask(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Constructs a DeadlineTask with a description and a specific date.
     *
     * @param description The description of the task.
     * @param date        The date of the deadline.
     */
    public DeadlineTask(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Checks if the task description or its date/time representation contains a
     * keyword.
     *
     * @param keyword The keyword to search for.
     * @return True if the keyword is found in the task description or date/time
     *         representation, false otherwise.
     */
    @Override
    public boolean isContaining(String keyword) {
        boolean superContains = super.isContaining(keyword);

        if (dateTime != null) {
            String formattedDateTime = formatDateTime(dateTime);
            return superContains || formattedDateTime.contains(keyword.toLowerCase());
        } else {
            String formattedDate = formatDate(date);
            return superContains || formattedDate.contains(keyword.toLowerCase());
        }
    }

    private String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")) + " " +
                dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a")).toLowerCase();
    }

    private String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("d/M/yyyy")) + " " +
                date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")).toLowerCase();
    }

    /**
     * Converts the task to a string representation suitable for saving to a file.
     *
     * @return A string representation of the task for saving to a file.
     */
    @Override
    public String toFileString() {
        if (dateTime != null) {
            return "D | " + super.toFileString() + " | "
                    + dateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } else {
            return "D | " + super.toFileString() + " | " + date.format(DateTimeFormatter.ofPattern("d/M/yyyy"));
        }
    }

    /**
     * Converts the task to a human-readable string representation.
     *
     * @return A human-readable string representation of the task.
     */
    @Override
    public String toString() {
        String formattedDate;

        if (dateTime != null) {
            formattedDate = dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a"));
        } else {
            formattedDate = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }

        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}
