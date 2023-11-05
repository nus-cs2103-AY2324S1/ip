package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class to create and store deadlines.
 * Extends Task.
 */
public class Deadline extends Task implements Comparable {
    private LocalDate by;

    /**
     * Public constructor for Deadline.
     *
     * @param val The description of the Deadline task.
     * @param by  The due date of the Deadline task in the format "MMM dd yyyy".
     */
    public Deadline(String val, String by) {
        super(val);
        assert !val.isEmpty() && !by.isEmpty();
        assert by.matches("\\w{3} \\d{2} \\d{4}");
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string containing the task type, description, and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Compares this Deadline task to another object.
     *
     * @param o The object to compare to.
     * @return A negative integer, zero, or a positive integer if this task is less than, equal to, or greater
     *         than the specified object.
     */
    @Override
    public int compareTo(Object o) {
        Deadline d = (Deadline) o;
        return this.by.compareTo(d.by);
    }
}

