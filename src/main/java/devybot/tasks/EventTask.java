package devybot.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The EventTask class represents a task with a specific event in the DevyBot
 * task management system.
 */
public class EventTask extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an EventTask with a description and specific start and end
     * date/time.
     *
     * @param description The description of the event.
     * @param from        The start date and time of the event.
     * @param to          The end date and time of the event.
     */
    public EventTask(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Checks if the task description or its date/time representations contain a
     * keyword.
     *
     * @param keyword The keyword to search for.
     * @return True if the keyword is found in the task description or date/time
     *         representations, false otherwise.
     */
    @Override
    public boolean isContaining(String keyword) {
        boolean superContains = super.isContaining(keyword);
        String formattedFromDateTime = formatDateTime(from);
        String formattedToDateTime = formatDateTime(to);

        return superContains || formattedFromDateTime.contains(keyword.toLowerCase())
                || formattedToDateTime.contains(keyword.toLowerCase());
    }

    private String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")) + " " +
                dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a")).toLowerCase();
    }

    /**
     * Converts the task to a string representation suitable for saving to a file.
     *
     * @return A string representation of the task for saving to a file.
     */
    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + from.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))
                + " | " + to.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    /**
     * Converts the task to a human-readable string representation.
     *
     * @return A human-readable string representation of the task.
     */
    @Override
    public String toString() {
        String formattedFrom = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
        String formattedTo = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
        return "[E]" + super.toString() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }
}
