/**
 * Deadline is a task with a deadline date.
 */
public class Deadline extends Task{
    /**
     * Deadline date
     */
    protected String date;

    /**
     * Constructs a new Deadline object
     * @param name Name of deadline
     * @param date Date of deadline
     */
    public Deadline(String name, String date) {
        super(name);
        this.date = date;
    }

    /**
     * Return string representation of Deadline
     * @return String representation of Deadline
     */
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.date);
    }
}
