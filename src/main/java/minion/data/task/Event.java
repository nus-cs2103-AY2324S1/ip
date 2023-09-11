package minion.data.task;

import java.time.LocalDateTime;

import minion.parser.DatetimeParser;

/**
 * Represents an minion.data.task.Event, a task that start at a specific date/time and ends at a specific date/time.
 */
public class Event extends Task {
    private LocalDateTime startDatetime;
    private LocalDateTime endDatetime;
    private String startDatetimeString;
    private String endDatetimeString;

    /**
     * Creates an event object. This is the main constructor of the minion.data.task.Event class.
     * @param description Description of the event.
     * @param isDone Whether the event has been done.
     * @param startDatetime Start date/time of event.
     * @param endDatetime End date/time of event.
     */
    public Event(String description, boolean isDone, LocalDateTime startDatetime, LocalDateTime endDatetime) {
        super(description, isDone);
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
        this.startDatetimeString = DatetimeParser.convertFromDatetime(startDatetime);
        this.endDatetimeString = DatetimeParser.convertFromDatetime(endDatetime);
        taskSymbol = TaskSymbol.EVENT.getSymbol();
    }

    /**
     * Creates an event object. This calls the main constructor when the default for isDone is false.
     * @param description Description of the event.
     * @param startDatetime Start date/time of event.
     * @param endDatetime End date/time of event.
     */
    public Event(String description, LocalDateTime startDatetime, LocalDateTime endDatetime) {
        this(description, false, startDatetime, endDatetime);
    }

    /**
     * Returns whether the event contains the query.
     * @param query the query.
     * @return whether the event contains the query.
     */
    @Override
    public boolean contains(String query) {
        return description.contains(query) || startDatetimeString.contains(query) || endDatetimeString.contains(query);
    }

    /**
     * Returns the string representation of the event.
     * @return string representation of the event.
     */
    @Override
    public String toString() {
        return "[" + taskSymbol + "]" + super.toString() + " (from: " + startDatetimeString + " to: "
            + endDatetimeString + ")";
    }

    /**
     * Returns a string representation of the event to be used in storage.
     * @return string representation of the event for storage.
     */
    @Override
    public String toStringStorage() {
        return taskSymbol + " | " + (isDone ? "1" : "0") + " | " + description + " | " + startDatetimeString + " - "
            + endDatetimeString;
    }

    /**
     * Checks whether this object equals the other object.
     * @param o other object.
     * @return whether this object equals the other object.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Event)) {
            return false;
        }
        Event t = (Event) o;
        return description.equals(t.description) && isDone == t.isDone
                && startDatetimeString.equals(t.startDatetimeString) && endDatetimeString.equals(t.endDatetimeString);
    }

    /**
     * Compares two event objects based on their datetimes
     * @param e other event object
     * @return an integer denoting the relative order of this task and the other task
     */
    public int compare(Event e) {
        int a = startDatetime.compareTo(e.startDatetime);
        if (a == 0) {
            return endDatetime.compareTo(e.endDatetime);
        }
        return a;
    }
}
