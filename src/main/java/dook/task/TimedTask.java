package dook.task;

import java.time.LocalDate;

/**
 * Guarantees that the task can be compared with local dates.
 */
public interface TimedTask {
    public abstract void processDateTimes();
    public abstract boolean isBefore(LocalDate dateTime);
    public abstract boolean isAfter(LocalDate dateTime);
    public abstract boolean isDuring(LocalDate dateTime);

}
