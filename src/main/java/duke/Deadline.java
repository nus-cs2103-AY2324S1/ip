package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Deadline extends Task{
    /**
     * Date and time that the deadline must be done by
     */
    LocalDateTime by;

    /**
     * Constructor for the Deadline class.
     *
     * @param name Name of the Deadline.
     * @param by DateTime that the deadline must be completed by.
     */
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representing the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(dateTimeOutputFormatter) + ")";
    }

    /**
     * Returns a string representation of the task to be saved.
     *
     * @return A string representing the deadline to be saved.
     */
    @Override
    public String toSaveStateString() {
        String[] state = new String[]{ "deadline", this.getDone() ? "1" : "0", this.getTaskName(),
                this.by.format(Duke.dateTimeInputFormatter) };
        return String.join(" / ", state);
    }

    /**
     * Returns whether the event is on a date.
     *
     * @return A boolean representation of whether the event is on a given date.
     */
    @Override
    public boolean isOnDate(LocalDate date) {
        return date.isEqual(this.by.toLocalDate());
    }
}
