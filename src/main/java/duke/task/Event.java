package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class inherits from Task class.
 * An event object encapsulates the name of task, status of task,
 * start date and time of task, end date and time of task.
 *
 * @author ruo-x
 */
public class Event extends Task {
    /** Start date and time of task */
    private final LocalDateTime start;

    /** End date and time of task */
    private final LocalDateTime end;

    /**
     * Constructor of an Event object.
     *
     * @param isDone Status of task.
     * @param taskName Name of task.
     * @param start Start of task as a LocalDateTime object.
     * @param end End of task as a LocalDateTime object.
     */
    public Event(boolean isDone, String taskName, LocalDateTime start, LocalDateTime end) {
        super(isDone, taskName);
        this.start = start;
        this.end = end;
    }

    @Override
    public LocalDateTime getTaskDue() {
        return this.start;
    }

    /**
     * Formats date and time of task into the desired format.
     *
     * @param dateTime Start or end of task as a LocalDateTime object.
     * @return Formatted date and time of task as a String.
     */
    public String getDateTimeString(LocalDateTime dateTime) {
        assert dateTime != null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy 'at' HH:mm");
        return dateTime.format(formatter);
    }

    /**
     * Returns a string format of an Event object to be displayed to the user.
     */
    @Override
    public String toString() {
        if (this.getStatus()) {
            return "[E]"
                    + "[X] "
                    + this.getName()
                    + "(from: " + getDateTimeString(this.start)
                    + " to: " + getDateTimeString(this.end) + ")";
        } else {
            return "[E]"
                    + "[ ] "
                    + this.getName()
                    + "(from: " + getDateTimeString(this.start)
                    + " to: " + getDateTimeString(this.end) + ")";
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toStoreString() {
        if (this.getStatus()) {
            return "E/@/1/@/" + this.getName() + "/@/" + this.start + "/@/" + this.end;
        } else {
            return "E/@/0/@/" + this.getName() + "/@/" + this.start + "/@/" + this.end;
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toUpdateString(int newStatus) {
        return "E/@/" + newStatus + "/@/" + this.getName() + "/@/" + this.start + "/@/" + this.end;
    }
}

