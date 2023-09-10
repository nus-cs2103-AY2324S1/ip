package teho.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific time frame.
 */
public class Event extends Task {
    private LocalDate fromDate;
    private LocalDate toDate;

    /**
     * Constructs new Event task with description, beginning date of event and
     * end date of event. The beginning date of event should be before or equal
     * to the end date of event.
     *
     * @param description Description of task with a specific time frame.
     * @param fromDate Beginning date of the task with a specific time frame.
     * @param toDate End date of the task with a specific time frame.
     */
    public Event(String description, LocalDate fromDate, LocalDate toDate) {
        super(description);
        if (fromDate.isAfter(toDate)) {
            throw new IllegalArgumentException("The from date cannot be after the to date!");
        }
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Returns a string representation of the Event task details.
     *
     * @return String representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon() + " " + this.description
                + " (from: "
                + fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: "
                + toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a string representation of the Event task details for saving task in file.
     *
     * @return String representation of the Event task.
     */
    @Override
    public String fileString() {
        String digitStatus = this.isDone? "1": "0";
        return "E|" + digitStatus + "|" + this.description + "|"
                + fromDate + "|" + toDate;
    }
}

