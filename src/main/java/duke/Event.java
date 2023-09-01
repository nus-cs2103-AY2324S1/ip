package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Creates a new Event task that is not in LocalDate format.
     * @param description   Description of an event task.
     * @param from  Start date of event task.
     * @param to    End date of event task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates a new Event task that is in LocalDate format.
     * @param description   Description of an event task.
     * @param from  Start date of event task.
     * @param to    End date of event task.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        this.to = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: "+ to +")";
    }
}

