package robert.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline variation of the <tt>Task</tt> class. Used to track
 * a deadline.
 *
 * @author Lee Zhan Peng
 */
public class Deadline extends Task {

    /** The date that the task is due on */
    private final LocalDate byDate;

    /**
     * Constructs Deadline using a description and a due date.
     *
     * @param description the description of the task.
     * @param by the due date.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.byDate = by;
    }

    /**
     * Constructs Deadline using a description, a due date, and an indication on whether it is marked.
     *
     * @param description the description of the task.
     * @param by the due date.
     * @param isDone whether the task is completed.
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.byDate = by;
    }

    /**
     * A getter of the due date.
     *
     * @return the due date.
     */
    public LocalDate getByDate() {
        return this.byDate;
    }

    /**
     * An identifier on whether the task is due on a particular date.
     *
     * @return a boolean.
     */
    public boolean isDueOn(LocalDate date) {
        return this.byDate.equals(date);
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return the string representation.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
