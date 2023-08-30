import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Deadline is a task with a deadline date.
 */
public class Deadline extends Task{
    /**
     * Deadline date
     */
    protected LocalDateTime by;

    /**
     * Constructs a new Deadline object
     * @param name Name of deadline
     * @param by Date of deadline
     */
    public Deadline(String name, String by) {
        super(name);
        this.by = LocalDateTime.parse(by, DATETIME_FORMAT);
    }

    /**
     * Return string representation of Deadline
     * @return String representation of Deadline
     */
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by.format(DATETIME_FORMAT));
    }

    @Override
    public String generateSaveString() {
        return String.format("D | %b | %s /by %s", isDone, name, by.format(DATETIME_FORMAT));
    }

    @Override
    public boolean isOccurringOnDate(LocalDate date) {
        return by.toLocalDate().equals(date);
    }
}
