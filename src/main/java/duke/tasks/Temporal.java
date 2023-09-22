package duke.tasks;

import java.time.LocalDateTime;

/**
 * Encapsulates a task that starts at a specific time and ends at a specific time.
 */
public interface Temporal {

    /**
     * Returns the start time of the temporal object.
     *
     * @return The start time of the temporal object.
     */
    LocalDateTime getStartTime();

    /**
     * Returns the end time of the temporal object.
     *
     * @return The end time of the temporal object.
     */
    LocalDateTime getEndTime();

    /**
     * Returns true if the temporal object is within the given period.
     *
     * @param start The start of the period.
     * @param end The end of the period.
     * @return True if the temporal object is within the given period.
     */
    boolean isWithinPeriod(LocalDateTime start, LocalDateTime end);

}
