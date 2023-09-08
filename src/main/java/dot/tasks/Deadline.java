package dot.tasks;

import java.time.LocalDateTime;

import dot.parser.Parser;

/**
 * The Deadline class implements the Deadline Task which instances
 * can be inserted into a TaskList with a Dateable deadline.
 */
public class Deadline extends Task {

    private final Dateable deadline;

    /**
     * Constructor for Deadline.
     *
     * @param description This is the description for the Task.
     * @param deadline    This is the deadline for the Task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = Dateable.of(deadline);
    }

    /**
     * This is the overloaded Constructor for Deadline.
     *
     * @param description This is the description for the Task.
     * @param deadline    This is the deadline for the Task.
     * @param isCompleted This is the boolean representing the completeness of the Deadline.
     */
    public Deadline(String description, String deadline, boolean isCompleted) {
        super(description, isCompleted);
        this.deadline = Dateable.of(deadline);
    }

    @Override
    public String getFileFormat() {
        return String.format("D | %s | %s", super.getFileFormat(),
                Parser.parseDisplayDatetimeToStorageDatetime(this.deadline.toString()));
    }

    @Override
    public boolean isOnDate(LocalDateTime startOfDay, LocalDateTime endOfDay) {
        assert startOfDay.isBefore(endOfDay) : "startOfDay should be before endOfDay";
        // Event can either start or end on the date itself, or both
        return this.deadline.isAfterOrOn(startOfDay) && this.deadline.isBeforeOrOn(endOfDay);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + String.format(" (by: %s)", this.deadline);
    }

}
