package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a description, completion status, and an event duration.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs an event task with the specified description, start date, and end date.
     *
     * @param description The description of the event task.
     * @param from The start date of the event in the format "yyyy-MM-dd".
     * @param to The end date of the event in the format "yyyy-MM-dd".
     */
    public Event(String description, String from, String to) {
        super(description);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        this.from = LocalDate.parse(from, inputFormatter);
        this.to = LocalDate.parse(to, inputFormatter);
    }

    /**
     * Returns a string representation of the event task,
     * including its completion status icon, description, start date, and end date.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (from: " + from.format(outputFormatter)
                + " to: " + to.format(outputFormatter) + ")";
    }
}
