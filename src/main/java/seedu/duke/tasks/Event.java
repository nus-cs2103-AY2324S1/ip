package seedu.duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class
 */
public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Event constructor.
     *
     * @param description user input
     * @param from date
     * @param to date
     * @param isMarked if task is already marked
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isMarked) {
        super(description, isMarked); // initializes its task
        this.from = from;
        this.to = to;
    }

    /**
     * Formats description for storage in duke.txt
     *
     * @return string representation
     */
    public String writeFormat() {
        int isDone = 0;
        if (super.isMarked()) {
            isDone = 1;
        }
        String formattedDateFrom = from.format(super.timeFormat);
        String formattedDateTo = to.format(super.timeFormat);
        return "E" + " | " + isDone + " | " + super.getDescription()
                + " | " + formattedDateFrom + " | " + formattedDateTo;
    };

    public LocalDateTime getFromDate() {
        return this.from;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))
                + " to:" + to.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + ")";
    }
}
