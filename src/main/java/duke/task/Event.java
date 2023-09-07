package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event in the list of tasks.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the description of the event that is written to the data file.
     *
     * @return String representation of event stored in the data file.
     */
    @Override
    public String writeFile() {
        return "E | " + super.writeFile() + " | " + this.from + " | " + this.to;
    }

    /**
     * Return the description of the event that is printed to the user.
     *
     * @return String representation of event printed to the user.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                LocalDate.parse(this.from).format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " +
                LocalDate.parse(this.to).format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
