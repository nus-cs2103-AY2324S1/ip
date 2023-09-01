package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task {

    protected LocalDate byDate;
    protected LocalTime byTime;

    public Deadline(String description, boolean isDone, LocalDate byDate, LocalTime byTime) {
        super(description, isDone);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    /**
     * Returns a String representation of this Deadline. The format is
     * "[D][StatusIcon] Description (by: Date/Time)"
     * 
     * @return a String representation of this Deadline.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s %s)", getStatusIcon(), description,
                (byDate != null ? byDate : ""), (byTime != null ? byTime : ""));
    }

    /**
     * Returns a string representation of this Deadline. The format is D # Doneness
     * # Description # Date/Time.
     * Note that this is different from toString() as it is used for encoding data
     * in the file.
     * 
     * @return a string representation of this Deadline for storage in the file.
     */
    @Override
    public String toFileString() {
        return String.format("D # %d # %s # %s %s", (isDone ? 1 : 0), description,
                (byDate != null ? byDate : ""), (byTime != null ? byTime : ""));
    }
}