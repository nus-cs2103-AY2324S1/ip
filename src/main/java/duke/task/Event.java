package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class is a task that has a start and end date.
 */
public class Event extends Task {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private LocalDate start;
    private LocalDate end;

    /**
     * The constructor for an Event.
     *
     * @param description The description of the task.
     * @param start The start date of the task.
     * @param end The end date of the task.
     */
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * This method is used to return the string representation of an Event.
     *
     * @return Returns the string representation of an Event.
     */
    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(), start.format(FORMAT), end.format(FORMAT));
    }
}
