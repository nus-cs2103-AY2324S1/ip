package duke;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * The Event class represents a task that has a start date and time and an end date and time, or just start and end dates.
 * It extends the Task class and adds fields to store the event's timing information.
 */
public class Event extends Task {

    protected LocalDateTime fromDateTime;
    protected LocalDate fromDate;
    protected LocalDateTime toDateTime;
    protected LocalDate toDate;

    /**
     * Constructs an Event task with a description and specific start and end date and time.
     *
     * @param description    The description of the Event task.
     * @param fromDateTime   The LocalDateTime representing the event's start date and time.
     * @param toDateTime     The LocalDateTime representing the event's end date and time.
     */
    public Event(String description, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(description);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    /**
     * Constructs an Event task with a description and specific start and end dates.
     *
     * @param description    The description of the Event task.
     * @param fromDate       The LocalDate representing the event's start date.
     * @param toDate         The LocalDate representing the event's end date.
     */
    public Event(String description, LocalDate fromDate, LocalDate toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Returns a string representation of the Event task, including its status icon, description, and event timing.
     * The date and time format used is "MMM dd yyyy HH:mm" (e.g., "Jan 01 2023 14:30") for LocalDateTime
     * and "MMM dd yyyy" (e.g., "Jan 01 2023") for LocalDate, with the English locale.
     *
     * @return A formatted string representing the Event task.
     */
    @Override
    public String toString() {
        String status = "[" + getStatusIcon() + "] ";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);

        if (fromDateTime != null && toDateTime != null) {
            return " " + "[E]" + status + description +
                    " (from: " + fromDateTime.format(formatter) +
                    " to: " + toDateTime.format(formatter) + ")";
        } else {
            return " " + "[E]" + status + description +
                    " (from: " + fromDate.format(dateTimeFormatter) +
                    " to: " + toDate.format(dateTimeFormatter) + ")";
        }
    }
}
