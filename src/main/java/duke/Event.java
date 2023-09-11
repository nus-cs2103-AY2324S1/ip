package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A duke.Task object that contains a start and end time
 */
public class Event extends Task{
    protected String from;
    protected String to;

    /**
     * Create an duke.Event duke.Task based on description, start time and
     * end time
     *
     * @param description The description of the task
     * @param from Start date of task
     * @param to End date of task
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        LocalDate.parse(from);
        LocalDate.parse(to);
    }

    /**
     * Get description of the event
     *
     * @return description of task, start and end time
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " +
                LocalDate.parse(from).format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " +
                LocalDate.parse(to).format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
