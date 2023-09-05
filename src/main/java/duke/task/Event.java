package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 */
public class Event extends Task {


    protected LocalDateTime dateTime;

    /**
     * Constructs an Event task.
     *
     * @param description The description of the event task.
     * @param dateTime The date and time of the event task.
     */
    public Event(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
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
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma"); // Print in 12-hour time format
        String timeString = this.dateTime.format(timeFormatter).toLowerCase();

        return "[E]" + super.toString()
            + " (at: " + this.dateTime.format(dateFormatter) + " " + timeString + ")";
    }
}
