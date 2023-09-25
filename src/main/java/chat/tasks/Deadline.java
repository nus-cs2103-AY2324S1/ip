package chat.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

/**
 * A task with description, completeness, and a due date/time.
 * @author juzzztinsoong
 */
public class Deadline extends Task {

    protected LocalDate byDate;
    protected LocalTime byTime;

    /**
     * Constructor method for Deadline.
     * @param description the description of the deadline. Cannot be empty.
     * @param isDone true if the deadline is done, false otherwise.
     * @param byDate the date to use for the deadline. Will not be displayed if null.
     * @param byTime the time to use for the deadline. Will not be displayed if null.
     */
    public Deadline(String description, boolean isDone, LocalDate byDate, LocalTime byTime) {
        super(description, isDone);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    /**
     * Returns a String representation of this Deadline. The format is
     * "[D][StatusIcon] Description (by: Date/Time)"
     * @return a String representation of this Deadline.
     */
    @Override
    public String toString() {
        String byDateString = byDate != null ? byDate.toString() : "";
        String byTimeString = byTime != null ? byTime.toString() : "";
        return String.format("[D][%s] %s (by: %s %s)", getStatusIcon(), description, byDateString, byTimeString);
    }

    /**
     * Returns a string representation of this Deadline. The format is D # Doneness
     * # Description # Date/Time.
     * Note that this is different from toString() as it is used for encoding data
     * in the file.
     * @return a string representation of this Deadline for storage in the file.
     */
    @Override
    public String toFileString() {
        String byDateString = byDate != null ? byDate.toString() : "";
        String byTimeString = byTime != null ? byTime.toString() : "";
        return String.format("D # %d # %s # %s %s", getDoneInt(), description, byDateString, byTimeString);
    }

    @Override
    public long getFirstDate() {
        LocalDate tempDate = byDate != null ? byDate : LocalDate.of(1970,1,1);
        LocalTime tempTime = byTime != null ? byTime : LocalTime.of(0,0);
        return LocalDateTime.of(tempDate, tempTime).toEpochSecond(ZoneOffset.UTC);
    }
}
