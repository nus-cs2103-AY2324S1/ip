package sana;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task of type event.
 */
public class Event extends Task {

    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs an event task object.
     *
     * @param description event task description.
     * @param from event start date.
     * @param to event end date.
     * @param isDone indicator of whether the task is done.
     */
    public Event(String description, LocalDate from, LocalDate to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns string representation of event task.
     *
     * @return string representation.
     */
    @Override
    public String toString() {
        String formattedFrom = this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedTo = this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }

    /**
     * Returns a formatted task string for saving to files.
     *
     * @return formatted task string for saving to files.
     */
    @Override
    public String formatTask() {
        return "E" + super.formatTask() + " | " + from + " | " + to;
    }


}
