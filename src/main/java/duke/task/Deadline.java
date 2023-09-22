package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * Class to represent a deadline task.
 */
public class Deadline extends Task implements Comparable<Deadline> {
    private static final String SYMBOL = "D";

    protected TemporalAccessor dueDatetime;

    /**
     * Constructor for Deadline.
     *
     * @param description Description of the deadline task.
     * @param dueDatetime Due date (and time) of the deadline task.
     */
    public Deadline(String description, TemporalAccessor dueDatetime) {
        this(description, dueDatetime, false);
    }

    /**
     * Constructor for Deadline.
     *
     * @param description Description of the deadline task.
     * @param dueDatetime Due date (and time) of the deadline task.
     * @param isCompleted Whether the deadline task is completed.
     */
    public Deadline(String description, TemporalAccessor dueDatetime, boolean isCompleted) {
        super(description, isCompleted);
        this.dueDatetime = dueDatetime;
    }

    @Override
    public String getDataString() {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd-MM-yyyy[ HHmm]");
        return String.join(" | ",
                Deadline.SYMBOL,
                super.isCompleted ? "1" : "0",
                super.getDescription(),
                parser.format(this.dueDatetime));
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)",
                Deadline.SYMBOL,
                super.toString(),
                super.formatDateTime(this.dueDatetime));
    }

    @Override
    public int compareTo(Deadline other) {
        if (this.dueDatetime instanceof LocalDate && other.dueDatetime instanceof LocalDate) {
            return ((LocalDate) this.dueDatetime).compareTo((LocalDate) other.dueDatetime);
        }

        if (this.dueDatetime instanceof LocalDateTime && other.dueDatetime instanceof LocalDateTime) {
            return ((LocalDateTime) this.dueDatetime).compareTo((LocalDateTime) other.dueDatetime);
        }

        if (this.dueDatetime instanceof LocalDate) {
            return 1;
        }

        if (other.dueDatetime instanceof LocalDate) {
            return -1;
        }

        return 0;
    }
}
