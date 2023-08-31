package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates an Event task that can be added to the task manager.
 *
 * @author Teo Kai Sheng
 */

public class Event extends Task {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");

    /**
     * Starting date of the event.
     */
    private LocalDate from;

    /**
     * Ending date of the event.
     */
    private LocalDate to;

    /**
     * Constructor to create an Event.
     * @param description Description of the event.
     * @param from Starting date of the event.
     * @param to Ending date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the String representation of the event.
     * @return A String representing the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatter.format(from) + " to: " + formatter.format(to) + ")";
    }

    /**
     * Returns the String representation of the event to be saved in the hard disk.
     * @return A String representing the event.
     */
    @Override
    public String taskToString() {
        return "E | " + super.taskToString() + " | " + from + " | " + to;
    }
}
