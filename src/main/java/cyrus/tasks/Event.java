package cyrus.tasks;

import java.time.LocalDate;

import com.google.gson.annotations.SerializedName;

import cyrus.utility.DateUtility;

/**
 * Event task that contains the name of the event as well as when the event starts (i.e. {@code
 * from}) and ends (i.e. {@code to}).
 */
public class Event extends Task {
    @SerializedName("type")
    private static final String TYPE = "event";
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Create Event with name and event to and from dates.
     *
     * @param name name of deadline task.
     * @param from date event starts from.
     * @param to   date event lasts to.
     */
    public Event(String name, LocalDate from, LocalDate to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format(
                "[E] %s (from: %s to: %s)",
                super.toString(),
                DateUtility.formatLocalDate(this.from),
                DateUtility.formatLocalDate(this.to)
        );
    }
}

