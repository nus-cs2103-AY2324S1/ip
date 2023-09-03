package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * The Event class represents a task that occurs within a specific time range.
 * It is a subclass of the Task class and provides methods to work with event tasks.
 */
public class Event extends Task {

    private LocalDateTime fromTime;
    private LocalDateTime toTime;

    /**
     * Constructs an Event task with the given task name and time range.
     *
     * @param taskName The name of the event task.
     * @param fromTime The start time of the event.
     * @param toTime The end time of the event.
     */
    public Event(String taskName, LocalDateTime fromTime, LocalDateTime toTime) {
        super(taskName);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    /**
     * Constructs an Event task with the given task name, completion status, and time range.
     *
     * @param taskName The name of the event task.
     * @param done The completion status of the task.
     * @param fromTime The start time of the event.
     * @param toTime The end time of the event.
     */
    public Event(String taskName, boolean done, LocalDateTime fromTime, LocalDateTime toTime) {
        super(taskName, done);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    /**
     * Overrides the toString() method to provide a custom string representation of the event task.
     *
     * @return A formatted string containing task details and time range.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm").withLocale(Locale.US);
        String formattedFromTime = fromTime.format(formatter);
        String formattedToTime = toTime.format(formatter);

        return "[E]" + super.toString() + " (from: " + formattedFromTime + " to: " + formattedToTime + ")";
    }

}

