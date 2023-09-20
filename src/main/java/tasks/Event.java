package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task, with a description, the start and the end date.
 */
public class Event extends Task {

    private String taskIcon = "[E]";
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * A public constructor to initialize a Deadline task
     *
     * @param description  a description of the task
     * @param startDate a datetime instance of the expected start of task
     * @param endDate a datetime instance of the expected end of task
     */
    public Event(String description, LocalDateTime startDate, LocalDateTime endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * A public constructor to initialize a Deadline task
     *
     * @param description  a description of the task
     * @param startDate a datetime instance of the expected start of task
     * @param endDate a datetime instance of the expected end of task
     * @param isDone task completion status
     */
    public Event(String description, LocalDateTime startDate, LocalDateTime endDate, boolean isDone) {
        // For functions.Load
        super(description, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    @Override
    public String getTaskAsString() {
        String message = String.format("%s[%s] %s (from: %s to: %s)", this.taskIcon, this.getStatusIcon(),
                this.getDescription(), this.getStartDate(), this.getEndDate());
        return message;
    }

    /**
     * Parses the LocalDateTime of the start datetime to a String representation
     *
     * @return A String representation of start LocalDateTime
     */
    public String getStartDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String formattedDateTime = this.startDate.format(formatter);
        return formattedDateTime;
    }

    /**
     * Parses the LocalDateTime of the end datetime to a String representation
     *
     * @return A String representation of end LocalDateTime
     */
    public String getEndDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String formattedDateTime = this.endDate.format(formatter);
        return formattedDateTime;
    }
}
