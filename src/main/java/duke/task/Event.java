package duke.task;

import duke.time.Time;

import java.time.LocalDateTime;

public class Event extends Task {
    /**
     * the type Icon
     */
    private String type = "E";
    /**
     * start of the event
     */
    private LocalDateTime from;
    /**
     * end of the event
     */
    private LocalDateTime to;

    /**
     * constructor for duke.task.Event duke.task
     * @param description the text stored
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = Time.toLocalDateTime(from);
        this.to = Time.toLocalDateTime(to);
    }

    /**
     * override the toString method
     * @return a string
     */
    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() +
                " (from: " + Time.toString(this.from) + " to: " + Time.toString(this.to) + ")";
    }

    @Override
    public String toDataString() {
        return this.type + " / " + super.toDataString() + " / " +
                Time.toDataString(this.from) + " / " + Time.toDataString(this.to);
    }
}
