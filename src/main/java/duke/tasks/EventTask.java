package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates an event with a start and end date and time.
 * Inherits from Task class.
 *
 * @author Wu Jingya
 */
public class EventTask extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an EventTask with the specified description and start and end date and time.
     *
     * @param description The task's description.
     * @param from The task's starting date and time.
     * @param to The task's ending date and time.
     */
    public EventTask(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an EventTask with the specified description, start and end date and time and completion status.
     *
     * @param description The task's description.
     * @param from The task's starting date and time.
     * @param to The task's ending date and time.
     * @param isDone Whether the task is completed.
     */
    public EventTask(String description, LocalDateTime from, LocalDateTime to, Boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String fromFormatted = from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        String toFormatted = to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        return "[E]" + super.toString() + " (from: " + fromFormatted
                + " " + "to: " + toFormatted + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toData() {
        return "E|" + super.toData() + "|" + from + "|" + to;
    }

    //For testing purposes only
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof EventTask) {
            boolean hasSameFrom = ((EventTask) obj).from.equals(this.from);
            boolean hasSameTo = ((EventTask) obj).to.equals(this.to);
            return super.equals(obj) && hasSameFrom && hasSameTo;
        }
        return false;
    }
}
