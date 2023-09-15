package dialogix.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents an event task in Dialogix.
 */
public class Event extends Task {
    private String eventTime;
    private Date eventDate;

    /**
     * Initializes an Event task with a description and an event time specified as a string.
     *
     * @param description The description of the task.
     * @param eventTime   The event time specified as a string.
     */
    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    /**
     * Initializes an Event task with a description and an event time specified as a date.
     *
     * @param description The description of the task.
     * @param eventDate   The event time specified as a date.
     */
    public Event(String description, Date eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    /**
     * Gets the formatted output string for saving in a file.
     *
     * @return The formatted output string.
     */
    @Override
    public String getOutputFormat() {
        if (eventTime == null) {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
            return String.format("E | %d | %s | %s", isDone() ? 1 : 0,
                    getDescription(), dateFormatter.format(eventDate));
        }
        return String.format("E | %d | %s | %s", isDone() ? 1 : 0, getDescription(), eventTime);
    }

    /**
     * Gets a string representation of the Event task.
     *
     * @return The string representation of the Event task.
     */
    @Override
    public String toString() {
        if (eventDate != null) {
            DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return String.format("[E]%s (at: %s)", super.toString(), dtf.format(eventDate));
        } else {
            return String.format("[E]%s (at: %s)", super.toString(), eventTime);
        }
    }
}
