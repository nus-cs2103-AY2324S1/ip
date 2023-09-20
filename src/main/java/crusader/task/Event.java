package crusader.task;

import java.util.Date;

import crusader.DateUtils;

/**
 * An event task, with a duration of time the event occurs.
 */
public class Event extends Task {
    /**
     * When the event starts
     */
    private final Date from;

    /**
     * When the event ends
     */
    private final Date to;

    /**
     * Constructs a new event.
     *
     * @param name  Name of the event.
     * @param from  When the event starts.
     * @param to    When the event ends.
     */
    public Event(String name, Date from, Date to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format(
                "[E]%s (from: %s to: %s)",
                super.toString(),
                DateUtils.presentFormat(this.from),
                DateUtils.presentFormat(this.to));
    }

    @Override
    public String toFormat() {
        return String.format(
                "E|%s|%s|%s|%s",
                super.getName(),
                super.isDone() ? "X" : " ",
                DateUtils.saveFormat(this.from),
                DateUtils.saveFormat(this.to));
    }
}
