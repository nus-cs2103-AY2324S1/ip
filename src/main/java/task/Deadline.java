package task;
import java.time.LocalDateTime;

/**
 * This class encapsulates a Deadline.
 */

public class Deadline extends Task {
    private String deadlineBy;
    private LocalDateTime deadlineInDateTime;

    /**
     * Instantiates a new Deadline.
     *
     * @param description the Deadline description
     * @param deadlineBy  deadline by time yyyy-MM-dd HH:mm
     */
    public Deadline(String description, String deadlineBy) {
        super(description);
        this.deadlineBy = deadlineBy;
        this.deadlineInDateTime = formatDateAndTime(deadlineBy);
    }

    /**
     * Instantiates a new Deadline.
     *
     * @param status      the status
     * @param description the Deadline description
     * @param deadlineBy  deadline by time yyyy-MM-dd HH:mm
     */
    public Deadline(int status, String description, String deadlineBy) {
        super(description, status != 0); //if 0, return false, else return true
        this.deadlineBy = deadlineBy;
        this.deadlineInDateTime = formatDateAndTime(deadlineBy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String storeToDiskFormat() {
        return "D" + "|" + this.diskStatus() + "|" + this.getDescription() + "|" + this.deadlineBy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + printDateTimeFormat(deadlineInDateTime) + ")";
    }
}
