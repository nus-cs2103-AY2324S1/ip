package duke.task;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * Class to represent a deadline task.
 */
public class Deadline extends Task {
    private static String SYMBOL = "D";
    
    protected TemporalAccessor dueDatetime;

    /**
     * Constructor for Deadline.
     * 
     * @param description Description of the deadline task.
     * @param dueDatetime Due date (and time) of the deadline task.
     */
    public Deadline(String description, TemporalAccessor dueDatetime) {
        super(description);
        this.dueDatetime = dueDatetime;
    }

    @Override
    public String getDataString() {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd-MM-yyyy[ HHmm]");
        return String.join(" | ",
                Deadline.SYMBOL,
                super.isDone ? "1" : "0",
                super.getDescription(),
                parser.format(this.dueDatetime));
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)",
                Deadline.SYMBOL,
                super.toString(),
                super.formatDateTime(this.dueDatetime));
    }
}
