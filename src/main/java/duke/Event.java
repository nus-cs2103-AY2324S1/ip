package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs a new {@code Event} object, with {@code isCompleted} set to false.
     *
     * @param details Details of the {@code Event}.
     * @param start Start date and time of the {@code Event}, stored as a {@code LocalDateTime} object.
     * @param end End date and time of the {@code Event}, stored as a {@code LocalDateTime} object.
     */
    public Event(String details, LocalDateTime start, LocalDateTime end) {
        super(details);
        this.start = start;
        this.end = end;
    }

    /**
     * Loads a {@code Event} object that was previously stored in the hard disk.
     * The {@code isCompleted} parameter corresponds to the completion status in the last
     * instance of {@code Duke}.
     *
     * @param details Details of the {@code Event}.
     * @param isCompleted Completion status of the {@code Event}.
     * @param start Start date and time of the {@code Event}, stored as a {@code LocalDateTime} object.
     * @param end End date and time of the {@code Event}, stored as a {@code LocalDateTime} object.
     */
    public Event(String details, boolean isCompleted, LocalDateTime start,
                 LocalDateTime end) {
        super(details, isCompleted);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the string representation of the {@code Event}, to be
     * printed in the {@code list} method.
     *
     * @return String representation of the {@code Event}.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String startDate = this.start.toLocalDate().format(dateFormatter);
        String endDate = this.end.toLocalDate().format(dateFormatter);
        String startTime = this.start.toLocalTime().format(timeFormatter);
        String endTime = this.end.toLocalTime().format(timeFormatter);
        return "[E]" + super.toString() + String.format(
                " (from: %s to: %s)",
                startDate + " " + startTime,
                endDate + " " + endTime);
    }
}
