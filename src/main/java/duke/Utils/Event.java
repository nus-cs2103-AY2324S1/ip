package duke.utils;

import java.time.LocalDateTime;

/**
 * The Event class represents a task with a specific start and end date and time in the Duke application.
 * It extends the Task class and includes additional information about the event.
 */
public class Event extends Task {

    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs a new Event object with the specified title, start date and time, and end date and time.
     *
     * @param title The title of the event task.
     * @param start The date and time when the event starts.
     * @param end   The date and time when the event ends.
     */
    protected Event(String title, LocalDateTime start, LocalDateTime end) {
        super(title, Task.Type.EVENT);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a new Event object with the specified title, marked status, start date and time,
     * and end date and time.
     *
     * @param title  The title of the event task.
     * @param marked A boolean indicating whether the task is marked as completed.
     * @param start  The date and time when the event starts.
     * @param end    The date and time when the event ends.
     */
    protected Event(String title, boolean marked, LocalDateTime start, LocalDateTime end) {
        this(title, start, end);
        if (marked) {
            this.mark();
        }
    }

    /**
     * Creates a new Event object from an array of arguments.
     *
     * @param args An array of strings containing information to create an Event object.
     * @return A new Event object created from the provided arguments.
     */
    protected static Event of(String[] args) {
        boolean marked = FileIO.assertBoolean(args[1]);
        String title = FileIO.assertString(args[2]);
        LocalDateTime start = FileIO.assertDateTime(args[3]);
        LocalDateTime end = FileIO.assertDateTime(args[4]);
        return new Event(title, marked, start, end);
    }

    /**
     * Converts the Event object to a CSV (Comma-Separated Values) string.
     *
     * @return A CSV string representation of the Event object.
     */
    @Override
    public String toCsv() {
        return FileIO.joinCsv(
            this.type(),
            this.marked(),
            this.name(),
            Task.dateToString(this.start),
            Task.dateToString(this.end)
        );
    }

    /**
     * Returns a string representation of the Event object, including its type and event time information.
     *
     * @return A string representation of the Event object.
     */
    @Override
    public String toString() {
        return this.type()
            + super.toString()
            + " (from: " + Task.dateToString(this.start)
            + " to: " + Task.dateToString(this.end)
            + ")";
    }
}
