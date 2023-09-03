package chatbot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    /** Start time of chatbot.task.Event. */
    protected LocalDate start;
    /** End time of chatbot.task.Event. */
    protected LocalDate end;

    /**
     * Constructor for chatbot.task.Event.
     *
     * @param description Description of task.
     * @param start Start time of event.
     * @param end End time of event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    /**
     * Constructor for chatbot.task.Event loaded from chatbot.Storage.
     *
     * @param description Description of task.
     * @param start Start time of event as LocalDate.
     * @param end End time of event as LocalDate.
     * */
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * toString method for chatbot.task.Event.
     *
     * @return String representation of eent.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: "+ end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
