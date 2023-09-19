package sillybot.tasks;

import java.time.LocalDate;

import sillybot.DatesAndTimesFormatter;

/**
 * Represents an EventTask object that is a Task.
 */
public class EventTask extends Task {
    protected String from;
    protected String to;
    protected LocalDate startDate;
    protected LocalDate endDate;

    /**
     * Creates an EventTask object.
     *
     * @param description The description of the EventTask.
     * @param from        The start date of the EventTask.
     * @param to          The end date of the EventTask.
     */
    public EventTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.startDate = Task.parseDate(from);
        this.endDate = Task.parseDate(to);

        if (startDate != null) {
            this.from = startDate.format(DatesAndTimesFormatter.OUTPUT_FORMAT.formatter);
        }

        if (endDate != null) {
            this.to = endDate.format(DatesAndTimesFormatter.OUTPUT_FORMAT.formatter);
        }
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
