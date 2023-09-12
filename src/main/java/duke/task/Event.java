package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 */
public class Event extends Task {

    /**
     * Constructs an Event task.
     *
     * @param description The description of the event task.
     * @param dateTime The date and time of the event task.
     */
    public Event(String description, LocalDateTime dateTime) {
        super(description);
        this.setDateTime(dateTime);
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description
            + " | " + this.dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public boolean isOnDate(LocalDate date) {
        return this.dateTime.toLocalDate().equals(date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
