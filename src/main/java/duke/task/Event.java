package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class that inherits from Task.
 */

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;


    /**
     * Constructor with description, from and to.
     * @param description the description of the event being added.
     * @param from the start time of the event.
     * @param to the end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Constructor with description, from, to and whether it has been marked as done.
     * @param description the description of the event being added.
     * @param from the start time of the event.
     * @param to the end time of the event.
     * @param isDone whether it has been marked as done.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * The toString representation of an event task.
     * @return the String representation of the task.
     */

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy ha")) + " to: "
                + to.format(DateTimeFormatter.ofPattern("MMM d yyyy ha")) + ")";
    }

    /**
     * The String representation of the task that will be written to the
     * text file.
     * @return The String representation of the task for the text file.
     */
    @Override
    public String toWriteString() {
        return "E | " + (isDone ? "X" : "0") +  " | " + this.description + " | " +
                from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + " | " +
                to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

}