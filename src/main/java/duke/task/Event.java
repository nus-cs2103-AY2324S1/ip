package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


/**
 * Represents a task with a start and end date.
 * */
public class Event extends Task {
    private String description;
    private LocalDate from;
    private LocalDate to;


    /**
     * Constructs an Event task.
     *
     * @param description The description of the task.
     * @param from        The start date of the event.
     * @param to          The end date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
        this.description = description;
    }

    /**
     * Returns a formatted string representation of the Event task.
     *
     * @return The formatted string representation.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "(from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a formatted string representation of the Event task for file storage.
     *
     * @return The formatted string representation for file storage.
     */
    public String toStringFile() {
        return "E | " + super.toStringFile() + "/from " + from + "/to " + to;
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task ("Event").
     */
    @Override
    public String getType() {
        return "Event";
    }
    /**
     * Compares this Event task with another object for equality.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event event) {
            if (obj == this) {
                return true;
            }
            return this.description.equals(event.description)
                    && this.to.equals(event.to) && this.from.equals(event.from);
        }
        return false;
    }
}
