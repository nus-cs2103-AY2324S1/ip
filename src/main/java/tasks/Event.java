package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private String TaskIcon = "[E]";
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
        String message = String.format("%s[%s] %s (from: %s to: %s)", this.TaskIcon,this.getStatusIcon(),
                this.getDescription(), this.getStartDate(), this.getEndDate());
        return message;
    }

    public String getStartDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String formattedDateTime = this.startDate.format(formatter);
        return formattedDateTime;
    }

    public String getEndDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String formattedDateTime = this.endDate.format(formatter);
        return formattedDateTime;
    }
}
