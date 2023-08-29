package dook.task;

import java.time.LocalDate;

public interface TimedTask {
    public abstract void processDateTimes();
    public abstract boolean isBefore(LocalDate dateTime);
    public abstract boolean isAfter(LocalDate dateTime);
    public abstract boolean isDuring(LocalDate dateTime);

}
