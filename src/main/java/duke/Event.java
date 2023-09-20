package duke;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * This class encapsulates an Event task that has the date/time to indicate when the event starts and end.
 */
public class Event extends Task {

    private static final int VALID_DAYS = 0;
    private static final int DUE_DAYS = 7;
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for Event.
     *
     * @param description the description of the event task.
     * @param from the date/time of when the event task starts.
     * @param to the date/time of when the event task ends.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + super.convertDateToString(from)
                + " to: " + super.convertDateToString(to) + ")";
    }

    @Override
    public String convertToSaveFormat() {
        return "E | " + super.convertToSaveFormat() + " | " + super.convertDateToString(from)
                + " to " + super.convertDateToString(to);
    }

    @Override
    public boolean isWithinDue() {
        long dueDays = Duration.between(LocalDateTime.now(), from).toDays();
        return dueDays >= VALID_DAYS && dueDays <= DUE_DAYS;
    }
}
