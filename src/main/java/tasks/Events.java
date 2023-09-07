package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The `Events` class represents a task that is an event with a specified date range.
 * It extends the `Task` class and adds functionality to handle event dates and provide
 * formatted representations of events.
 */
public class Events extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs an `Events` task.
     *
     * @param name   The name of the event task.
     * @param from   The starting date of the event.
     * @param to     The ending date of the event.
     * @param isDone Whether the event task is marked as done or not.
     * @throws DateTimeParseException If there's an issue parsing the date strings.
     */
    public Events(String name, String from, String to, boolean isDone) throws DateTimeParseException {
        super(name, isDone);
        this.from = LocalDate.parse(from.trim());
        this.to = LocalDate.parse(to.trim());
    }

    /**
     * Returns a formatted string representation of the `Events` task, including its name,
     * status, and date range.
     *
     * @return A formatted string representing the `Events` task.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + "(from : " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Marks the `Events` task as done and provides feedback.
     */
    @Override
    public String markDone() {
        super.markDone();
        return "Oki, I've marked this event task as done: \n" + this.toString();
    }

    /**
     * Unmarks the `Events` task as done and provides feedback.
     */
    @Override
    public String unmarkDone() {
        super.unmarkDone();
        return "Damn bro...unmarked this event task :( : \n" + this.toString();
    }
}
