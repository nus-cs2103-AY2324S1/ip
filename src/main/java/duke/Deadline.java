package duke;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * This class encapsulates a Deadline task that has the date/time to indicate when the task is due.
 */
public class Deadline extends Task {

    private static final int VALID_DAYS = 0;
    private static final int DUE_DAYS = 7;
    protected LocalDateTime by;

    /**
     * Constructor for Deadline.
     *
     * @param description the description of the deadline task.
     * @param by the date/time of when the deadline task is due.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.convertDateToString(by) + ")";
    }

    @Override
    public String convertToSaveFormat() {
        return "D | " + super.convertToSaveFormat() + " | "
                + super.convertDateToString(by);
    }

    @Override
    public boolean isWithinDue() {
        long dueDays = Duration.between(LocalDateTime.now(), by).toDays();
        return dueDays >= VALID_DAYS && dueDays <= DUE_DAYS;
    }
}
