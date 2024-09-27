package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor of the Event class.
     * @param description The description of the Task.
     * @param from When the event starts.
     * @param to When the event ends.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the String representation of the Task that is to be stored in the specified file.
     * @return The String representation of the Task that is to be stored in the specified file.
     */
    @Override
    public String toWrite() {
        return "[E]" + super.toWrite() + "(from: " + this.from.toString() + " to: " + this.to.toString() + ")";
    }

    /**
     * Returns the String representation of the Task that is printed.
     * @return The String representation of the Task that is printed.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
