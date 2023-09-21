package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class to represent and create Events.
 * Extends the Task class.
 */
public class Event extends Task implements Comparable {

    private LocalDate from;
    private LocalDate to;

    /**
     * Public constructor for Event.
     *
     * @param val  The description of the Event.
     * @param from The starting date of the Event in the format "MMM dd yyyy".
     * @param to   The ending date of the Event in the format "MMM dd yyyy".
     */
    public Event(String val, String from, String to) {
        super(val);
        assert !val.isEmpty() && !from.isEmpty() && !to.isEmpty();
        assert from.matches("\\w{3} \\d{2} \\d{4}");
        assert to.matches("\\w{3} \\d{2} \\d{4}");
        this.from = LocalDate.parse(from, DateTimeFormatter.ofPattern("MMM dd yyyy"));
        this.to = LocalDate.parse(to, DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Returns a string representation of the Event.
     *
     * @return A string containing the task type, description, and date range.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Compares this Event task to another object based on the starting date.
     *
     * @param o The object to compare to.
     * @return A negative integer, zero, or a positive integer if this task's starting date is less than, equal to,
     *         or greater than the specified object's starting date.
     */
    @Override
    public int compareTo(Object o) {
        Event e = (Event) o;
        return this.from.compareTo(e.from);
    }
}
