import java.time.LocalDateTime;

/**
 * This is the Deadline class, a child class of Task class
 * @author Selwyn
 */
public class Deadline extends Task{
    /**
     * Field representing the date and time of the deadline
     */
    protected LocalDateTime endDateTime;

    /**
     * Constructor for a Deadline task
     *
     * @param detail
     */
    public Deadline(String detail, LocalDateTime endDateTime) {
        super(detail);
        this.endDateTime = endDateTime;
    }

    /**
     * This method returns the string representation of a deadline task
     * @return String representation of a deadline task
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + super.getDisplayDateTime(this.endDateTime) + ")";
    }
}
