package thea;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 * This class has from and to which are the characteristics of an Event task.
 * This class is a subclass of the abstract class Task.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs a new Event object.
     * Converts the from and to from String to LocalDateTime.
     *
     * @param taskName description of the task.
     * @param from date and time when the event starts.
     * @param to date and time when the event ends.
     */
    public Event(String taskName, String from, String to) {
        super(taskName);
        String[] dateYearMonthDayFrom = from.split(" ")[0].split("-");
        String[] timeHourMinuteFrom = from.split(" ")[1].split(":");
        String[] dateYearMonthDayTo = to.split(" ")[0].split("-");
        String[] timeHourMinuteTo = to.split(" ")[1].split(":");
        this.from = LocalDateTime.of(Integer.parseInt(dateYearMonthDayFrom[0]),
                Integer.parseInt(dateYearMonthDayFrom[1]),
                Integer.parseInt(dateYearMonthDayFrom[2]),
                Integer.parseInt(timeHourMinuteFrom[0]),
                Integer.parseInt(timeHourMinuteFrom[1]));
        this.to = LocalDateTime.of(Integer.parseInt(dateYearMonthDayTo[0]),
                Integer.parseInt(dateYearMonthDayTo[1]),
                Integer.parseInt(dateYearMonthDayTo[2]),
                Integer.parseInt(timeHourMinuteTo[0]),
                Integer.parseInt(timeHourMinuteTo[1]));
    }

    /**
     * Returns the task in memory format, which means the format
     * of which the task is saved to the hard disk.
     *
     * @return task in memory format.
     */
    @Override
    public String toMemoryFormat() {
        return String.format("E | %s | %s | %s | %s", (super.isDone ? "1" : "0"),
                super.taskName, this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    /**
     * Returns the task in desired string format.
     *
     * @return task in string format.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)",
                super.isDone ? "X" : " ", super.taskName,
                this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")),
                this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")));
    }
}
