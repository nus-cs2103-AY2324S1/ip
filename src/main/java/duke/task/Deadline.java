package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {

    /**
     * Constructs a Deadline task.
     *
     * @param description The description of the deadline task.
     * @param dateTime The date and time of the deadline task.
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.setDateTime(dateTime);
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description
            + " | " + this.dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public boolean isOnDate(LocalDate date) {
        return this.dateTime.toLocalDate().equals(date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
