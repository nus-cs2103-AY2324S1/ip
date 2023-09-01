package duke.task;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * Class to represent an event task.
 */
public class Event extends Task {
    private static String SYMBOL = "E";
    
    protected TemporalAccessor startDatetime;
    protected TemporalAccessor endDatetime;

    /**
     * Constructor for Event.
     * 
     * @param description Description of the event task.
     * @param startDatetime Start date (and time) of the event task.
     * @param endDatetime End date (and time) of the event task.
     */
    public Event(String description, TemporalAccessor startDatetime, TemporalAccessor endDatetime) {
        super(description);
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
    }

    @Override
    public String getDataString() {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd-MM-yyyy[ HHmm]");
        return String.join(" | ",
                Event.SYMBOL, super.isDone ? "1" : "0",
                super.getDescription(),
                parser.format(startDatetime),
                parser.format(endDatetime));
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)",
                Event.SYMBOL, super.toString(),
                super.formatDateTime(this.startDatetime),
                super.formatDateTime(this.endDatetime));
    }
}
