package jerma.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructor for a Event Task
     * 
     * @param description The description of the task
     * @param by          Date when the deadline should be complete by
     * @throws DateTimeParseException Thrown if date is not parseable
     */
    public Event(String descriptor, String from, String to) {
        super(descriptor);
        this.symbol = "E";
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Returns string representation of Event Task for saving purposes
     * 
     * @return String representation of Event Task for saving purposes
     */
    @Override
    public String save() {
        return String.format("%s|%s|%s|%s", this.symbol, super.save(),
                this.from, this.to);
    }

    /**
     * Returns string representation of Event Task
     * 
     * @return String representation of Event Task
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (%s to %s)", this.symbol, super.toString(),
                this.from, this.to);
    }
}
