package duke.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task in the Duke application.
 */
public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs an Event task with the given name, start time, and end time.
     *
     * @param name  The name of the event task.
     * @param start The start time of the event as a string.
     * @param end   The end time of the event as a string.
     */
    public Event(String name, String start, String end) {
        super(name);
        this.start = timeConverterStart(start);
        this.end = timeConverterEnd(end);
    }

    /**
     * Converts a start time string representation to a LocalDateTime object.
     *
     * @param time The start time as a string.
     * @return The LocalDateTime object representing the start time.
     * @throws DateTimeException If there's an issue parsing the time string.
     */
    public LocalDateTime timeConverterStart(String time) throws DateTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        if (!time.contains(" ")) {
            time += " 0000";
        }
        return LocalDateTime.parse(time, formatter);
    }

    /**
     * Converts an end time string representation to a LocalDateTime object.
     *
     * @param time The end time as a string.
     * @return The LocalDateTime object representing the end time.
     * @throws DateTimeException If there's an issue parsing the time string.
     */
    public LocalDateTime timeConverterEnd(String time) throws DateTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        if (!time.contains(" ")) {
            time += " 2359";
        }
        return LocalDateTime.parse(time, formatter);
    }

    /**
     * Converts the event task to a string for saving.
     *
     * @return A string representation of the task for saving.
     */
    @Override
    public String toSave() {
        String startToSave = start.toString().replace("T", " ").replace(":", "");
        String endToSave = end.toString().replace("T", " ").replace(":", "");
        return (super.isComplete ? "1 " : "0 ") + "event " + super.name + "/from " + startToSave + " /to " + endToSave;
    }

    /**
     * Converts the event task to a string.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "/from " + start.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                        + " /to " + end.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }
}
