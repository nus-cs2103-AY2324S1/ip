package helpbuddy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import helpbuddy.exception.HelpBuddyException;

/**
 * This class is a subclass of Task which defines an event task.
 */
public class Event extends Task {
    /** A String containing start of event. */
    private LocalDateTime from;
    /** A String containing end of event. */
    private LocalDateTime to;

    /**
     * A constructor of event
     * @param description Name of event
     * @param from Start of event
     * @param to End of event
     * @throws HelpBuddyException If description, start and end time of event is empty.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) throws HelpBuddyException {
        super(description);
        this.from = from;
        this.to = to;
        if (description.isBlank()) {
            throw new HelpBuddyException("The description of a event cannot be empty.\n");
        } else if (from == null) {
            throw new HelpBuddyException("Please enter /from followed by a start time of event.\n");
        } else if (to == null) {
            throw new HelpBuddyException("Please enter /to followed by an end time of event.\n");
        } else if (to.isBefore(from)) {
            throw new HelpBuddyException("End time must be after the start time!\n");
        } else if (to.isEqual(from)) {
            throw new HelpBuddyException("Both start and end time are the same, please check!\n");
        }
    }
    /**
     * @return String representation of event object
     */
    @Override
    public String toString() {
        String startTime = this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        String endTime = this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }

    @Override
    public String stringifyTask() {
        return String.format("E|%d|%s|%s", this.isDone ? 1 : 0, this.description, this.from + " to " + this.to);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Event) {
            Event task = (Event) obj;
            return this.stringifyTask().equals(task.stringifyTask());
        }

        return false;
    }

}
