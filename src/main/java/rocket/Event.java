package rocket;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Make an event task
     * @param description event description
     * @param from event start
     * @param to event end
     */
    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(Parser.prettyDateTimeFormatter) + " to: "
                + to.format(Parser.prettyDateTimeFormatter) + ")";
    }
}
