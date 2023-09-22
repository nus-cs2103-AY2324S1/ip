package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.Task;

/**
 * Represents an event task that spans a specific time period.
 * An Event task contains a description, a start date and time, and an end date and time.
 */
public class Event extends Task {

    private LocalDateTime fromDateAndTime;
    private LocalDateTime toDateAndTime;

    /**
     * Constructs an Event task with the specified description, start date and time, and end date and time.
     *
     * @param description     The description of the Event task.
     * @param fromDateAndTime The start date and time of the event.
     * @param toDateAndTime   The end date and time of the event.
     */
    public Event(String description, LocalDateTime fromDateAndTime, LocalDateTime toDateAndTime) {
        super(description, Type.EVENT);
        this.fromDateAndTime = fromDateAndTime;
        this.toDateAndTime = toDateAndTime;
    }

    /**
     * Returns a string representation of the Event task.
     * The string includes the task type, status icon, description, and event time period.
     *
     * @return A formatted string representing the Event task.
     */
    @Override
    public String toString() {
        // example Oct 15 2019 2pm
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d yyyy ha");
        return "[E]" + "[" + getStatusIcon() + "] " + description + " (from: " + fromDateAndTime.format(formatter)
            + " to: " + toDateAndTime.format(formatter) + ")";
    }

    // Solution adapted and inspired from https://chat.openai.com/share/954ddc6e-dc2b-4f78-a9af-69f865aeff12

    /**
     * Converts the Event task to a formatted string for storage in a file.
     *
     * @return A string representing the Event task in the format suitable for storage.
     */

    @Override
    public String toFileString() {
        String statusIcon = (isDone ? "1" : "0");
        String formattedFromDateTime = fromDateAndTime.format(DateTimeFormatter.ofPattern("MMMM d yyyy ha"));
        String formattedToDateTime = toDateAndTime.format(DateTimeFormatter.ofPattern("MMMM d yyyy ha"));
        return "E " + "| " + statusIcon + " | " + getDescription() + " | " + formattedFromDateTime + " - "
            + formattedToDateTime + "\n";
    }
}
