package duke.tasklist;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 * This class extends the base Task class and includes specific start and end times for the event.
 */
class Event extends Task {
    private final LocalDate timeFrom;
    private final LocalDate timeTo;

    /**
     * Constructs an Event task with the given name and event times.
     *
     * @param name The name or description of the event.
     * @param timeFrom The start time of the event.
     * @param timeTo The end time of the event.
     */
    Event(String name, LocalDate timeFrom, LocalDate timeTo) {
        super(name);
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    /**
     * Returns the text representation of the Event task.
     * This includes the name, start time, and end time.
     *
     * @return The text representation of the Event task.
     */
    @Override
    public String getText() {
        return super.getText() + " | " + timeFrom + " | " + timeTo;
    }

    /**
     * Returns the formatted string representation of the Event task.
     * This includes the task type, name, and formatted start and end times.
     *
     * @return The formatted string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: "
                + timeFrom.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: "
                + timeTo.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}
