package jo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task in the `Jo` application.
 * It includes properties such as a description, start date, and end date.
 */
public class Event extends Task {

    protected LocalDate start;
    protected LocalDate end;

    /**
     * Constructs a new `Event` object with the specified description, completion status, start date, and end date.
     * @param description The description of the event task.
     * @param isDone      `true` if the event task is marked as done, `false` if it is undone.
     * @param start       The start date of the event.
     * @param end         The end date of the event.
     */
    public Event(String description, boolean isDone, LocalDate start, LocalDate end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a formatted string representation of the event task.
     * @return A string in the format: "[E][Status] Description (from: StartDate to: EndDate)".
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)",
                this.getStatusIcon(),
                this.description,
                this.start.format(DateTimeFormatter.ofPattern("d MMM yyyy")),
                this.end.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
        );
    }

    /**
     * Converts the event task to a string format suitable for storing in a file.
     * @return A string in the format: "E | Status | Description | StartDate | EndDate".
     */
    @Override
    public String toFile() {
        return String.format("E | %s | %s | %s | %s",
                this.isDone ? "1" : "0", this.description, this.start, this.end);
    }

    /**
     * Retrieves the end date of the event task.
     * @return The end date of the event.
     */
    public LocalDate getDeadline() {
        return this.end;
    }

}
