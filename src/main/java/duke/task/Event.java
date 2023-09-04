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

    /**
     * Formats date and time of task into the desired format.
     *
     * @param dateTime Start or end of task as a LocalDateTime object.
     * @return Formatted date and time of task as a String.
     */
    public String getDateTimeString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy 'at' HH:mm");
        return dateTime.format(formatter);
    }

    /**
     * Returns a string format of an Event object.
     *
     * @return String format of an Event object.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[E]"
                    + "[X] "
                    + this.taskName
                    + "(from: " + getDateTimeString(this.start)
                    + " to: " + getDateTimeString(this.end) + ")";
        } else {
            return "[E]"
                    + "[ ] "
                    + this.taskName
                    + "(from: " + getDateTimeString(this.start)
                    + " to: " + getDateTimeString(this.end) + ")";
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toStoreString() {
        if (this.isDone) {
            return "E/@/1/@/" + this.taskName + "/@/" + this.start + "/@/" + this.end;
        } else {
            return  "E/@/0/@/" + this.taskName + "/@/" + this.start + "/@/" + this.end;
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toUpdateString(int i) {
        return "E/@/" + i + "/@/" + this.taskName + "/@/" + this.start + "/@/" + this.end;
    }
}

