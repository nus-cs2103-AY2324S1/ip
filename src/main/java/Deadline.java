import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A task that has a deadline.
 */
public class Deadline extends Task{
    private final LocalDate date;
    private final LocalTime time;

    /**
     * Creates a deadline task instance.
     *
     * @param description Description of the task.
     * @param date Deadline date of the task.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
        this.time = null;
    }

    /**
     * Creates a deadline task instance with a specific time.
     *
     * @param description Description of the task.
     * @param date Deadline date of the task.
     * @param time Deadline time of the task.
     */
    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }
    /**
     * Returns a string representation of the task.
     *
     * @return Desired string representation of the task.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + this.timeString() + ")";
    }

    private String timeString() {
        return (this.time != null)
                ? " " + this.time.format(DateTimeFormatter.ofPattern("h:mm a"))
                : "";
    }

    /**
     * Returns a string representation of the task to be inserted into a file.
     *
     * @return Desired string representation of the task.
     */
    @Override
    public String toStringInFile() {
        return "[D]" + super.toStringInFile() + " " + this.date
                + this.timeStringInFile();
    }

    private String timeStringInFile() {
        return this.time != null
                ? " " + this.time.format(DateTimeFormatter.ofPattern("HH:mm"))
                : "";
    }
}
